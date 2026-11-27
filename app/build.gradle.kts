import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    // generateExtensionInfo, the asset copies, verifyApk and releaseManifest (build-logic/).
    id("doppio.app")
}

fun prop(name: String): String =
    providers.gradleProperty(name).orNull ?: error("gradle.properties has no $name")

// The release signing key stays out of git: keystore.properties in the repository root names the
// key file and holds its passwords. Without it every build is signed with the machine's debug key,
// so the project still builds anywhere (but such APKs can't update a release-key build).
val keystoreProps = Properties().apply {
    val f = rootProject.file("keystore.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}

// Build stamp: the git commit this APK was built from, plus "-dirty" when built with uncommitted
// changes, so every installed build can be told apart. It is never part of the version name.
fun git(vararg args: String): String = providers.exec {
    commandLine("git", *args)
    isIgnoreExitValue = true
}.standardOutput.asText.get().trim()
val buildStamp = git("rev-parse", "--short=7", "HEAD").ifEmpty { "nogit" } +
    (if (git("status", "--porcelain", "--untracked-files=no").isNotEmpty()) "-dirty" else "")

android {
    namespace = "com.alex.karoo.doppio.app"
    compileSdk = libs.versions.compileSdk.get().toInt()

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.alex.karoo.doppio"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = prop("doppio.versionCode").toInt()
        versionName = prop("doppio.versionName")
        buildConfigField("String", "BUILD_STAMP", "\"$buildStamp\"")
        // Where the Karoo and the update check find the latest version. To test the update card,
        // build with -PmanifestUrl=http://localhost:8791/manifest.json. Never publish such a build.
        manifestPlaceholders["manifestUrl"] = (findProperty("manifestUrl") as String?)
            ?: "${prop("doppio.repoUrl")}/releases/latest/download/manifest.json"
    }

    signingConfigs {
        if (keystoreProps.getProperty("storeFile") != null) {
            create("release") {
                storeFile = rootProject.file(keystoreProps.getProperty("storeFile"))
                storePassword = keystoreProps.getProperty("storePassword")
                keyAlias = keystoreProps.getProperty("keyAlias")
                keyPassword = keystoreProps.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        // The release key when keystore.properties exists, else the machine's debug key. Debug
        // builds take the release key too, so they install over a release build and keep its data.
        val signing = signingConfigs.findByName("release") ?: signingConfigs.getByName("debug")
        getByName("debug") {
            signingConfig = signing
        }
        getByName("release") {
            signingConfig = signing
            isMinifyEnabled = false
        }
        // Ride build R0: installs beside Doppio with its own ids (gradle.properties, doppio.probe.*).
        create("probe") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".probe"
            matchingFallbacks += "debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }

    lint {
        abortOnError = true
        // targetSdk 34 is on purpose: from 35 on, an app that creates a PendingIntent must opt in
        // to background activity launches, and the Karoo runs Android 12.
        disable += "ExpiredTargetSdkVersion"
    }
}

dependencies {
    implementation(project(":karoo-ext"))
    implementation(project(":karoo-doppio-kit"))
    implementation(project(":karoo-doppio-ui"))
    implementation(project(":karoo-doppio-ui-android"))
    implementation(project(":app-core"))
    implementation(project(":app-shell"))
    implementation(project(":app-field"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.timber)
    // The debug tools read the field fixtures on the emulator.
    debugImplementation(testFixtures(project(":app-core")))
    testImplementation(testFixtures(project(":app-core")))
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}

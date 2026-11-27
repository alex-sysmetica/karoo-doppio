// The Android half of the field: real fonts, icon paths and the painter.
plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.alex.karoo.doppio.app.field"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }

    lint {
        abortOnError = true
    }
}

dependencies {
    api(project(":app-core"))
    // androidx.core.graphics.PathParser, for the icon paths.
    implementation(libs.androidx.core.ktx)
    // The gallery, the painter lab and MetricsDump read the field fixtures in debug builds.
    debugImplementation(testFixtures(project(":app-core")))
    testImplementation(testFixtures(project(":app-core")))
    testImplementation(libs.junit)
}

// Hammerhead karoo-ext 1.1.9 built from source (lib/src/main of the upstream repo).
// Upstream: https://github.com/hammerheadnav/karoo-ext (Apache License 2.0, see LICENSE).
// Vendored at tag 1.1.9. One file differs from it: internal/Emitter.kt (see the notice there).
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.hammerhead.karooext"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        buildConfigField("String", "LIB_VERSION", "\"1.1.9\"")
    }

    buildFeatures {
        buildConfig = true
        aidl = true
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.timber)
}

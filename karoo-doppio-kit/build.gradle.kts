// The ride kit: app-agnostic code adapted from other Karoo apps (see LICENSE and FORK.md).
plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.alex.karoo.doppio.kit"
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
    api(project(":karoo-ext"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.timber)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}

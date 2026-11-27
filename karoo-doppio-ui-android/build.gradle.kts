// The Android half of the UI kit: the host, the element views, the palette and font seams.
plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.alex.karoo.doppio.ui.android"
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
    api(project(":karoo-doppio-ui"))
    // KeyButtonIcons.on is a StateFlow in the public API.
    api(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}

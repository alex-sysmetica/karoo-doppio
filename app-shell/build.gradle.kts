// Doppio's screens as pure functions on the UI kit: plain Kotlin, tested on the JVM.
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(libs.versions.java.get()))
    }
}

dependencies {
    api(project(":app-core"))
    api(project(":karoo-doppio-ui"))
    testImplementation(libs.junit)
    // The screen tests read fixture JSON: the library only, no serialization plugin.
    testImplementation(libs.kotlinx.serialization.json)
}

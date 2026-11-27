// Everything that needs no Android class and no SDK: plain Kotlin, tested on the JVM.
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    `java-test-fixtures`
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
    api(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
}

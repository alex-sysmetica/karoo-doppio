plugins {
    `kotlin-dsl`
}

dependencies {
    // AGP's public API, to compile against. At run time the build's own AGP provides it.
    compileOnly(libs.android.gradle.api)
    testImplementation(libs.junit)
}

gradlePlugin {
    plugins {
        register("doppioRoot") {
            id = "doppio.root"
            implementationClass = "com.alex.karoo.doppio.buildlogic.DoppioRootPlugin"
        }
        register("doppioApp") {
            id = "doppio.app"
            implementationClass = "com.alex.karoo.doppio.buildlogic.DoppioAppPlugin"
        }
    }
}

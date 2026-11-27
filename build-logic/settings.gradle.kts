// The build's own tasks, as two plugins: doppio.root (applied by the root build) and doppio.app
// (applied by :app). Included from the root settings with pluginManagement.includeBuild.
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"

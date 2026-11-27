pluginManagement {
    // The build's own tasks (checkModuleGraph, generateExtensionInfo, ...) live in build-logic/.
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "karoo-doppio"

// Hammerhead's karoo-ext 1.1.9 (Apache 2.0), built from source so no package token is needed.
include(":karoo-ext")

// The reusable kits: they never depend on the app's modules.
include(":karoo-doppio-kit")
include(":karoo-doppio-ui")
include(":karoo-doppio-ui-android")

// The app.
include(":app-core")
include(":app-shell")
include(":app-field")
include(":app")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    // checkModuleGraph, parityCheck and the strict test switches (build-logic/).
    id("doppio.root")
}

// Build output goes to "build.nosync" folders: iCloud Drive (if Documents is synced) skips
// folders ending in .nosync, so it doesn't create "file 2" copies that break the build.
allprojects {
    layout.buildDirectory.set(layout.projectDirectory.dir("build.nosync"))
}

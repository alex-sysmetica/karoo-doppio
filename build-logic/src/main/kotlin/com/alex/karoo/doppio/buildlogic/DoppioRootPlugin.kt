package com.alex.karoo.doppio.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

/** The JVM test task of every module: what a pull request and a release run. */
val JVM_TEST_TASKS = listOf(
    ":app-core:test",
    ":karoo-doppio-ui:test",
    ":app-shell:test",
    ":karoo-doppio-kit:testDebugUnitTest",
    ":karoo-doppio-ui-android:testDebugUnitTest",
    ":app-field:testDebugUnitTest",
    ":app:testDebugUnitTest",
)

/** The three strict switches. A test reads one as `System.getProperty(name) == "true"`. */
val STRICT_SWITCHES = listOf("doppio.strictGen", "doppio.strictStrings", "doppio.strictTrust")

/** The task that turns every strict switch on by being in the task graph. */
const val RELEASE_MANIFEST_TASK = "releaseManifest"

/**
 * Applied by the root build: registers `checkModuleGraph` and `parityCheck`, and hands the strict
 * switches to every test task of every module.
 */
class DoppioRootPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        check(project == project.rootProject) { "doppio.root belongs to the root build only" }
        registerCheckModuleGraph(project)
        registerParityCheck(project)
        wireStrictSwitches(project)
    }
}

/**
 * Sets each strict switch as a system property of every test task in the graph: "true" when the
 * Gradle property of that name is `true` or when `releaseManifest` is in the graph, else "false".
 * So `./gradlew :app:releaseManifest` runs the tests with all three on, with no `-P` flag.
 */
private fun wireStrictSwitches(root: Project) {
    val fromProperties = STRICT_SWITCHES.associateWith { name ->
        root.providers.gradleProperty(name).orNull == "true"
    }
    root.gradle.taskGraph.whenReady {
        val release = allTasks.any { it.name == RELEASE_MANIFEST_TASK }
        allTasks.filterIsInstance<Test>().forEach { test ->
            STRICT_SWITCHES.forEach { name ->
                test.systemProperty(name, (release || fromProperties.getValue(name)).toString())
            }
        }
    }
}

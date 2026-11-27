// STUB: owned by B5
package com.alex.karoo.doppio.buildlogic

import org.gradle.api.Project

/**
 * Writes the update manifest of a release. Registered empty, so its name exists from the first
 * day. It already depends on every JVM test task: being in the graph turns the strict switches
 * on (see [DoppioRootPlugin]).
 */
fun registerReleaseManifest(project: Project) {
    project.tasks.register(RELEASE_MANIFEST_TASK) {
        group = "release"
        description = "Writes the update manifest for a release (not filled yet)."
        dependsOn(JVM_TEST_TASKS)
    }
}

// STUB: owned by B3
package com.alex.karoo.doppio.buildlogic

import org.gradle.api.Project

/**
 * Runs the JVM tests that read the parity fixtures. A local task: the fixtures are not part of
 * this repository. Registered empty, so its name exists from the first day.
 */
fun registerParityCheck(root: Project) {
    root.tasks.register("parityCheck") {
        group = "verification"
        description = "Runs the parity tests with the fixtures present (not filled yet)."
        dependsOn(
            ":app-core:test",
            ":app-shell:test",
            ":app-field:testDebugUnitTest",
            ":app:testDebugUnitTest",
        )
    }
}

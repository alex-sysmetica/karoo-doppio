// STUB: owned by B3
package com.alex.karoo.doppio.buildlogic

import org.gradle.api.Project

/** Inspects the built APKs. Registered empty, so its name exists from the first day. */
fun registerVerifyApk(project: Project) {
    project.tasks.register("verifyApk") {
        group = "verification"
        description = "Inspects the built APKs (not filled yet)."
    }
}

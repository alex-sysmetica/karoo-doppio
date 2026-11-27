package com.alex.karoo.doppio.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Applied by `:app`, after the Android application plugin: the extension XML and its ids, the
 * asset copies, `verifyApk` and `releaseManifest`. Each task file registers its own task and
 * wires its own inputs, so a package that fills a task changes only that file.
 */
class DoppioAppPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.withPlugin("com.android.application") {
            registerGenerateExtensionInfo(project)
            registerAssetCopies(project)
            registerVerifyApk(project)
            registerReleaseManifest(project)
        }
    }
}

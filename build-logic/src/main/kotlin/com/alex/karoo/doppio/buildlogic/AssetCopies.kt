package com.alex.karoo.doppio.buildlogic

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileTree
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

/** Copies one file into a generated assets folder under a new name. */
abstract class FileAsset : DefaultTask() {
    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val source: RegularFileProperty

    @get:Input
    abstract val assetName: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun copy() {
        val dir = outputDir.get().asFile
        dir.deleteRecursively()
        dir.mkdirs()
        source.get().asFile.copyTo(dir.resolve(assetName.get()))
    }
}

/**
 * Copies the files of a folder that match one pattern into a generated assets folder, keeping
 * their paths below that folder. A missing or empty folder copies nothing and builds.
 */
abstract class FolderAssets : DefaultTask() {
    @get:Internal
    abstract val root: DirectoryProperty

    @get:Input
    abstract val pattern: Property<String>

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val files: FileTree
        get() = root.asFileTree.matching { include(pattern.get()) }

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun copy() {
        val dir = outputDir.get().asFile
        dir.deleteRecursively()
        dir.mkdirs()
        files.visit {
            if (!isDirectory) file.copyTo(relativePath.getFile(dir))
        }
    }
}

/**
 * The asset copies of `:app`:
 * - every variant: `THIRD_PARTY_NOTICES.md` as the asset `notices.txt` (the Licences screen);
 * - debug only: the field fixtures as `fixtures/field/<file>` and the screen fixtures as
 *   `fixtures/screens/<file>`, for the tools that read them on the emulator. Both folders are
 *   git-ignored and may be absent.
 */
fun registerAssetCopies(project: Project) {
    val components = project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
    val repo = project.rootProject.layout.projectDirectory
    components.onVariants { variant ->
        val name = variant.name.replaceFirstChar(Char::uppercase)
        val assets = variant.sources.assets ?: return@onVariants
        val notices = project.tasks.register("copy${name}NoticesAsset", FileAsset::class.java) {
            source.set(repo.file("THIRD_PARTY_NOTICES.md"))
            assetName.set("notices.txt")
        }
        assets.addGeneratedSourceDirectory(notices, FileAsset::outputDir)
        if (variant.buildType != "debug") return@onVariants
        val fixtures = mapOf(
            "Field" to (repo.dir("app-core/src/testFixtures/resources") to "fixtures/field/**"),
            "Screen" to (repo.dir("app-shell/src/test/resources") to "fixtures/screens/**"),
        )
        fixtures.forEach { (kind, from) ->
            val task = project.tasks.register(
                "copy${name}${kind}FixtureAssets",
                FolderAssets::class.java,
            ) {
                root.set(from.first)
                pattern.set(from.second)
            }
            assets.addGeneratedSourceDirectory(task, FolderAssets::outputDir)
        }
    }
}

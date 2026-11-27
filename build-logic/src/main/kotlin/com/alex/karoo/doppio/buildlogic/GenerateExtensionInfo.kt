package com.alex.karoo.doppio.buildlogic

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.BuildConfigField
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

/** The three ids of one build type, read from `gradle.properties`. */
data class ExtensionIds(val extensionId: String, val typePrefix: String, val fieldCount: Int) {
    init {
        require(extensionId.isNotBlank()) { "the extension id is empty" }
        require(typePrefix.isNotBlank()) { "the type prefix is empty" }
        require(fieldCount >= 1) { "the field count is $fieldCount" }
    }
}

/**
 * Fills `extension_info.template.xml`.
 *
 * - `{{extensionId}}` and `{{typePrefix}}` are replaced everywhere.
 * - The lines between the marker lines `<!-- {{#slots}} -->` and `<!-- {{/slots}} -->` repeat
 *   once per slot, 1 to the field count, with `{{slot}}` replaced by the slot number. The marker
 *   lines themselves are dropped.
 * - Anything else in double braces fails, so a typo never reaches the Karoo.
 */
object ExtensionTemplate {
    const val SLOTS_START = "<!-- {{#slots}} -->"
    const val SLOTS_END = "<!-- {{/slots}} -->"

    fun expand(template: String, ids: ExtensionIds): String {
        val lines = template.lines()
        val start = lines.indexOfFirst { it.trim() == SLOTS_START }
        val end = lines.indexOfFirst { it.trim() == SLOTS_END }
        require(start >= 0 && end > start) { "the template needs one $SLOTS_START block" }
        require(lines.count { it.trim() == SLOTS_START } == 1) { "more than one slot block" }
        val block = lines.subList(start + 1, end)
        val slots = (1..ids.fieldCount).flatMap { n ->
            block.map { it.replace("{{slot}}", n.toString()) }
        }
        val text = (lines.subList(0, start) + slots + lines.subList(end + 1, lines.size))
            .joinToString("\n")
            .replace("{{extensionId}}", ids.extensionId)
            .replace("{{typePrefix}}", ids.typePrefix)
        val left = Regex("\\{\\{[^}]*}}").find(text)
        require(left == null) { "unknown placeholder ${left?.value} in the template" }
        return text
    }
}

/** Writes `xml/extension_info.xml` of one variant into a generated resource folder. */
abstract class GenerateExtensionInfo : DefaultTask() {
    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val template: RegularFileProperty

    @get:Input
    abstract val extensionId: Property<String>

    @get:Input
    abstract val typePrefix: Property<String>

    @get:Input
    abstract val fieldCount: Property<Int>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val ids = ExtensionIds(extensionId.get(), typePrefix.get(), fieldCount.get())
        val xml = ExtensionTemplate.expand(template.get().asFile.readText(), ids)
        val dir = outputDir.get().asFile
        dir.deleteRecursively()
        dir.resolve("xml").mkdirs()
        dir.resolve("xml/extension_info.xml").writeText(xml)
    }
}

/**
 * The ids of a variant: the `doppio.probe.*` properties in a `probe` build, else the permanent
 * `doppio.*` ones. One source for the XML and for `BuildConfig`.
 */
private fun idsOf(project: Project, buildType: String?): ExtensionIds {
    val prefix = if (buildType == "probe") "doppio.probe." else "doppio."
    fun prop(name: String): String = project.providers.gradleProperty(prefix + name).orNull
        ?: error("gradle.properties has no $prefix$name")
    return ExtensionIds(prop("extensionId"), prop("typePrefix"), prop("fieldCount").toInt())
}

fun registerGenerateExtensionInfo(project: Project) {
    val components = project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
    val all = project.tasks.register("generateExtensionInfo") {
        group = "build"
        description = "Writes the extension XML of every variant from gradle.properties."
    }
    components.onVariants { variant ->
        val ids = idsOf(project, variant.buildType)
        val task = project.tasks.register(
            "generate${variant.name.replaceFirstChar(Char::uppercase)}ExtensionInfo",
            GenerateExtensionInfo::class.java,
        ) {
            template.set(project.layout.projectDirectory.file("extension_info.template.xml"))
            extensionId.set(ids.extensionId)
            typePrefix.set(ids.typePrefix)
            fieldCount.set(ids.fieldCount)
        }
        all.configure { dependsOn(task) }
        variant.sources.res?.addGeneratedSourceDirectory(task, GenerateExtensionInfo::outputDir)
        variant.buildConfigFields?.apply {
            val id = "\"${ids.extensionId}\""
            val prefix = "\"${ids.typePrefix}\""
            put("EXTENSION_ID", BuildConfigField("String", id, "Extension id"))
            put("TYPE_PREFIX", BuildConfigField("String", prefix, "Type-id prefix"))
            put("FIELD_COUNT", BuildConfigField("int", ids.fieldCount, "Number of fields"))
        }
    }
}

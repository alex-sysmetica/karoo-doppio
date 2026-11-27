package com.alex.karoo.doppio.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

/** One `project()` dependency: who declares it, on whom, in which configuration. */
data class ModuleEdge(
    val from: String,
    val to: String,
    val configuration: String,
    val testFixtures: Boolean,
) {
    fun encode(): String = "$from|$to|$configuration|$testFixtures"

    override fun toString(): String =
        "$from -> ${if (testFixtures) "testFixtures($to)" else to} in $configuration"

    companion object {
        fun decode(line: String): ModuleEdge {
            val (from, to, configuration, fixtures) = line.split('|')
            return ModuleEdge(from, to, configuration, fixtures.toBoolean())
        }
    }
}

/** The module table: the only `project()` dependencies the build may have. */
object ModuleTable {
    private val app = setOf(
        ":karoo-ext", ":karoo-doppio-kit", ":karoo-doppio-ui", ":karoo-doppio-ui-android",
        ":app-core", ":app-shell", ":app-field",
    )

    /** Each module and the modules it may depend on. */
    val allowed: Map<String, Set<String>> = mapOf(
        ":karoo-ext" to emptySet(),
        ":karoo-doppio-kit" to setOf(":karoo-ext"),
        ":karoo-doppio-ui" to emptySet(),
        ":karoo-doppio-ui-android" to setOf(":karoo-doppio-ui"),
        ":app-core" to emptySet(),
        ":app-shell" to setOf(":app-core", ":karoo-doppio-ui"),
        ":app-field" to setOf(":app-core"),
        ":app" to app,
    )

    /** The one module whose test fixtures others may use, and who may use them. */
    const val FIXTURE_MODULE = ":app-core"
    val fixtureUsers = setOf(":app-field", ":app")

    /**
     * A test fixtures edge is allowed in a test configuration and in `debugImplementation`.
     * A module's own `testFixtures…` configurations are not test configurations.
     */
    fun fixtureConfiguration(name: String): Boolean =
        (name.startsWith("test") && !name.startsWith("testFixtures")) ||
            name == "debugImplementation"

    /** Every edge that breaks the table, as a readable line. Empty when the graph is clean. */
    fun violations(modules: Collection<String>, edges: Collection<ModuleEdge>): List<String> {
        val unknown = modules.filter { it != ":" && it !in allowed }
            .map { "$it is not in the module table" }
        val bad = edges.filterNot(::isAllowed).map { "$it is not in the module table" }
        return unknown + bad
    }

    private fun isAllowed(edge: ModuleEdge): Boolean = when {
        // A module's own wiring, for example its test fixtures in its own tests.
        edge.from == edge.to -> true
        edge.testFixtures -> edge.to == FIXTURE_MODULE && edge.from in fixtureUsers &&
            fixtureConfiguration(edge.configuration)
        else -> edge.to in allowed[edge.from].orEmpty()
    }
}

/** Fails when a `project()` dependency of any module is outside the module table. */
abstract class CheckModuleGraph : DefaultTask() {
    @get:Input
    abstract val modules: ListProperty<String>

    @get:Input
    abstract val edges: ListProperty<String>

    @TaskAction
    fun check() {
        val violations = ModuleTable.violations(modules.get(), edges.get().map(ModuleEdge::decode))
        if (violations.isNotEmpty()) {
            throw GradleException(
                "The module graph breaks the module table:\n" +
                    violations.joinToString("\n") { "  $it" },
            )
        }
        logger.lifecycle("Module graph: ${edges.get().size} project edges, all in the table")
    }
}

fun registerCheckModuleGraph(root: Project) {
    val task = root.tasks.register("checkModuleGraph", CheckModuleGraph::class.java) {
        group = "verification"
        description = "Fails on a project() dependency outside the module table."
    }
    // Read the graph once every module is configured.
    root.gradle.projectsEvaluated {
        val found = root.subprojects.flatMap { module ->
            module.configurations.flatMap { configuration ->
                configuration.dependencies.withType(ProjectDependency::class.java).map { dep ->
                    ModuleEdge(
                        from = module.path,
                        to = dep.path,
                        configuration = configuration.name,
                        testFixtures = dep.capabilitySelectors.any {
                            "test-fixtures" in it.displayName
                        },
                    )
                }
            }
        }.distinct()
        task.configure {
            modules.set(root.subprojects.map { it.path })
            edges.set(found.map(ModuleEdge::encode))
        }
    }
}

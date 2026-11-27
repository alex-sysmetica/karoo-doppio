package com.alex.karoo.doppio.buildlogic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ModuleTableTest {
    private val modules = ModuleTable.allowed.keys

    private fun edge(from: String, to: String, conf: String = "implementation", fix: Boolean = false) =
        ModuleEdge(from, to, conf, fix)

    @Test
    fun acceptsEveryEdgeOfTheTable() {
        val edges = ModuleTable.allowed.flatMap { (from, tos) -> tos.map { edge(from, it, "api") } }
        assertEquals(emptyList<String>(), ModuleTable.violations(modules, edges))
    }

    @Test
    fun acceptsTheTwoTestAndTheTwoDebugFixtureEdges() {
        val edges = listOf(":app-field", ":app").flatMap {
            listOf(
                edge(it, ":app-core", "testImplementation", fix = true),
                edge(it, ":app-core", "debugImplementation", fix = true),
            )
        }
        assertEquals(emptyList<String>(), ModuleTable.violations(modules, edges))
    }

    @Test
    fun acceptsAModulesOwnWiring() {
        val own = edge(":app-core", ":app-core", "testImplementation", fix = true)
        assertEquals(emptyList<String>(), ModuleTable.violations(modules, listOf(own)))
    }

    @Test
    fun refusesAKitThatDependsOnTheApp() {
        val bad = edge(":karoo-doppio-ui", ":app-core")
        assertEquals(1, ModuleTable.violations(modules, listOf(bad)).size)
    }

    @Test
    fun refusesTheShellSeeingTheFieldModule() {
        val bad = edge(":app-shell", ":app-field")
        assertEquals(1, ModuleTable.violations(modules, listOf(bad)).size)
    }

    @Test
    fun refusesFixturesInAReleaseConfigurationOrAnotherModule() {
        val bad = listOf(
            edge(":app", ":app-core", "implementation", fix = true),
            edge(":app", ":app-core", "releaseImplementation", fix = true),
            edge(":app-shell", ":app-core", "testImplementation", fix = true),
        )
        assertEquals(3, ModuleTable.violations(modules, bad).size)
    }

    @Test
    fun refusesAModuleOutsideTheTable() {
        val found = ModuleTable.violations(modules + ":extra", emptyList())
        assertTrue(found.single().startsWith(":extra"))
    }
}

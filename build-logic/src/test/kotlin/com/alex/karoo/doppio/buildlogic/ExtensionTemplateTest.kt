package com.alex.karoo.doppio.buildlogic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class ExtensionTemplateTest {
    private val template = """
        <ExtensionInfo id="{{extensionId}}">
            <!-- {{#slots}} -->
            <DataType typeId="{{typePrefix}}{{slot}}" displayName="@string/host_blend_{{slot}}" />
            <!-- {{/slots}} -->
        </ExtensionInfo>
    """.trimIndent()

    @Test
    fun repeatsTheSlotBlockOncePerField() {
        val xml = ExtensionTemplate.expand(template, ExtensionIds("doppio", "doppio-", 3))
        assertEquals(
            """
            <ExtensionInfo id="doppio">
                <DataType typeId="doppio-1" displayName="@string/host_blend_1" />
                <DataType typeId="doppio-2" displayName="@string/host_blend_2" />
                <DataType typeId="doppio-3" displayName="@string/host_blend_3" />
            </ExtensionInfo>
            """.trimIndent(),
            xml,
        )
    }

    @Test
    fun takesTheProbeIdsWhenGivenThem() {
        val xml = ExtensionTemplate.expand(template, ExtensionIds("doppioprobe", "probe-", 3))
        assertTrue("id=\"doppioprobe\"" in xml)
        assertTrue("typeId=\"probe-3\"" in xml)
        assertFalse("probe-4" in xml)
    }

    @Test
    fun refusesAnUnknownPlaceholder() {
        assertThrows(IllegalArgumentException::class.java) {
            ExtensionTemplate.expand(
                template.replace("{{extensionId}}", "{{extensionID}}"),
                ExtensionIds("doppio", "doppio-", 1),
            )
        }
    }

    @Test
    fun refusesATemplateWithoutASlotBlock() {
        assertThrows(IllegalArgumentException::class.java) {
            ExtensionTemplate.expand("<ExtensionInfo />", ExtensionIds("doppio", "doppio-", 1))
        }
    }

    @Test
    fun refusesAFieldCountBelowOne() {
        assertThrows(IllegalArgumentException::class.java) { ExtensionIds("doppio", "doppio-", 0) }
    }
}

package dev.kord.codegen.kotlinpoet.test

import dev.kord.codegen.kotlinpoet.delegate.produceByName
import kotlin.test.Test
import kotlin.test.assertEquals

class NameDelegateTest {
    private class MockNamedSpec(val name: String)

    @Test
    fun `test name delegate`() {
        val name by produceByName { MockNamedSpec(it) }
        assertEquals("name", name.name)
    }
}

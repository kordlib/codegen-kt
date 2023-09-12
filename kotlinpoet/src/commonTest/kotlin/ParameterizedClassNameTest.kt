package dev.kord.codegen.kotlinpoet.test

import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import dev.kord.codegen.kotlinpoet.parameterizedBy
import kotlin.test.Test
import kotlin.test.assertEquals

class ParameterizedClassNameTest {
    @Test
    fun `test parameterizedBy`() {
        val list = List::class.asClassName().parameterizedBy<String>()

        assertEquals(typeNameOf<List<String>>(), list)
    }
}

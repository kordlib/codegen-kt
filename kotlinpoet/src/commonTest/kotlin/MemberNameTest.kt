@file:OptIn(DelicateKotlinPoetApi::class)

package dev.kord.codegen.kotlinpoet.test

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.asClassName
import dev.kord.codegen.kotlinpoet.asMemberName
import kotlin.test.Test
import kotlin.test.assertEquals

private enum class TestEnum {
    A
}

private val property = "string"

private fun function() = Unit

class MemberNameTest {
    private enum class NestedTestEnum {
        A
    }

    private val nestedProperty = "string"
    private fun nestedFunction() = Unit

    @Test
    fun `test top level enum constant member name`() {
        val memberName = TestEnum.A.asMemberName()
        assertEquals(TestEnum::class.asClassName().member("A"), memberName)
    }

    @Test
    fun `test nested enum constant member name`() {
        val memberName = NestedTestEnum.A.asMemberName()
        assertEquals(NestedTestEnum::class.asClassName().member("A"), memberName)
    }

    @Test
    fun `test top level property`() {
        val memberName = ::property.asMemberName()
        assertEquals(MemberName("dev.kord.codegen.kotlinpoet.test", "property"), memberName)
    }

    @Test
    fun `test nested property`() {
        val memberName = ::nestedProperty.asMemberName()
        assertEquals(MemberNameTest::class.asClassName().member("nestedProperty"), memberName)
    }

    @Test
    fun `test external property`() {
        val memberName = String::length.asMemberName()
        assertEquals(String::class.asClassName().member("length"), memberName)
    }

    @Test
    fun `test top level function`() {
        val memberName = ::function.asMemberName()
        assertEquals(MemberName("dev.kord.codegen.kotlinpoet.test", "function"), memberName)
    }

    @Test
    fun `test nested function`() {
        val memberName = ::nestedFunction.asMemberName()
        assertEquals(MemberNameTest::class.asClassName().member("nestedFunction"), memberName)
    }

    @Test
    fun `test external function`() {
        val memberName = String::plus.asMemberName()
        assertEquals(String::class.asClassName().member("plus"), memberName)
    }
}

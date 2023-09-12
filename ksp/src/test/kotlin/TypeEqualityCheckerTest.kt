import com.google.devtools.ksp.symbol.*
import dev.kord.codegen.ksp.isOfType
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private fun mockReference(referencedName: String, qualifiedName: String) = mockk<KSTypeReference> {
    every { element } returns mockk<KSClassifierReference> {
        every { referencedName() } returns referencedName
    }
    every { resolve() } returns mockKSType(referencedName, qualifiedName)
}

@Suppress("SameParameterValue")
private fun mockTypeAliasReference(
    aliasName: String,
    qualifiedAliasName: String,
    referencedName: String,
    qualifiedName: String
) = mockk<KSTypeReference> {
    every { element } returns mockk<KSClassifierReference> {
        every { referencedName() } returns aliasName
    }
    every { resolve() } returns mockk<KSType> {
        every { declaration } returns mockk<KSTypeAlias> alias@{
            every { name } returns MockKSName(aliasName)
            every { this@alias.qualifiedName } returns MockKSName(qualifiedAliasName)
            every { resolve() } returns mockKSType(referencedName, qualifiedName)
        }
    }
}

private fun mockKSType(referencedName: String, qualifiedName: String) = mockk<KSType> {
    every { declaration } returns mockk<KSClassDeclaration> clazz@{
        every { simpleName } returns MockKSName(referencedName)
        every { this@clazz.qualifiedName } returns MockKSName(qualifiedName)
    }
}


class TypeEqualityCheckerTest {
    @Test
    fun `test equal type returns true`() {
        val type = mockReference("Any", "kotlin.Any")

        assertIsSameType(type, "kotlin.Any")
    }

    @Test
    fun `test equal qualified type returns true`() {
        val type = mockReference("kotlin.Any", "kotlin.Any")

        assertIsSameType(type, "kotlin.Any")
    }


    @Test
    fun `test same simple name fails if qualified name is different`() {
        val type = mockReference("Any", "kotlin2.Any")

        assertIsNotSameType(type, "kotlin.Any")
    }

    @Test
    fun `check type alias returns true`() {
        val type = mockTypeAliasReference(
            "Test",
            "kord.Test",
            "Any",
            "kotlin.Any"
        )

        assertIsSameType(type, "kotlin.Any", canBeTypeAlias = true)
    }

    @Test
    fun `check type alias to qualified name returns true`() {
        val type = mockTypeAliasReference(
            "Test",
            "kord.Test",
            "kotlin.Any",
            "kotlin.Any"
        )

        assertIsSameType(type, "kotlin.Any", canBeTypeAlias = true)
    }
}

private fun assertIsSameType(actual: KSTypeReference, expected: String, canBeTypeAlias: Boolean = false) =
    assertTrue(actual.isOfType(expected, canBeTypeAlias))

private fun assertIsNotSameType(actual: KSTypeReference, expected: String, canBeTypeAlias: Boolean = false) =
    assertFalse(actual.isOfType(expected, canBeTypeAlias))

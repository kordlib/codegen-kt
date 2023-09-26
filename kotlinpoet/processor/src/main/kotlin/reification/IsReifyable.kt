package dev.kord.codegen.generator.reification

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.*
import kotlin.reflect.KClass

val ignoreList = listOf("addParameter", "addProperty")

/**
 * Checks whether a [KSFunctionDeclaration] is reifiable.
 *
 * A function is reifiable if any of its parameters conforms to the following rules:
 * - is not a vararg
 * - is either a KClass, CLassName or TypeName
 */
fun KSFunctionDeclaration.isReifiable(
    includeKClass: Boolean = true,
    includeClassName: Boolean = true,
    includeTypeName: Boolean = true
): Boolean {
    if (simpleName.asString() in ignoreList && parentDeclaration is KSClassDeclaration) return false
    // We cannot add two receivers at this point
    if (parentDeclaration is KSClassDeclaration && extensionReceiver != null) return false
    return parameters.any {
        val name = it.type.resolve().declaration.qualifiedName?.asString() ?: return@any false

        !it.isVararg && (name == KClass::class.qualifiedName && includeKClass
            || (name == ClassName::class.qualifiedName && includeClassName)
            || (name == TypeName::class.qualifiedName && includeTypeName))
    }
}

/**
 * Checks whether a [ParameterSpec] is reifiable.
 *
 * A parameter is reifiable if it conforms to the following rules:
 * - is not a vararg
 * - is either a KClass, CLassName or TypeName
 */
fun ParameterSpec.isReifiable(includeKClass: Boolean = true, includeClassName: Boolean = true): Boolean =
    KModifier.VARARG !in modifiers && type.isReifiable(includeKClass, includeClassName)

private fun TypeName.isReifiable(includeKClass: Boolean = true, includeClassName: Boolean = true): Boolean {
    val type = ((this as? ParameterizedTypeName)?.rawType ?: this).copy(nullable = false)
    return (type == KClass::class.asClassName() && includeKClass)
        || (type == ClassName::class.asClassName() && includeClassName)
        || type == TypeName::class.asClassName()
}

package dev.kord.codegen.generator.builder_functions

import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterSpec

// AnnotationSpec.get()
// FunSpec.builder()
// FunSpec.setterBuilder()
private val factoryFunctionRegex = """get|builder|(\w+)Builder""".toRegex()

fun KSClassDeclaration.getBuilderScopeName(packageName: String) =
    ClassName(packageName, simpleName.asString() + "BuilderScope")

/**
 * Representation of a spec factory function.
 *
 * @property hasBuilder whether the factory function has a builder
 * @param function the [KSFunctionDeclaration]
 * @property specType the [KSClassDeclaration] of the type the factory produces
 * @property specialName whether the function should have a special name (e.g., TypeSpec.classBuilder -> class)
 */
data class FactoryFunction(
    val hasBuilder: Boolean,
    private val function: KSFunctionDeclaration,
    val specType: KSClassDeclaration,
    val specialName: String?
) : KSFunctionDeclaration by function {
    /**
     * The name of the builder function produced.
     */
    val builderFunctionName: String
        get() = if (hasBuilder) {
            specialName ?: specType.simpleName.asString()
        } else {
            specType.simpleName.asString()
        }

    /**
     * The name of the builder lambda typealias in [packageName].
     */
    fun getBuilderScopeName(packageName: String) = specType.getBuilderScopeName(packageName)

    /**
     * The [ParameterSpec] of the builder parameter in [packageName].
     */
    fun getBuilderParameter(packageName: String) =
        ParameterSpec.builder("block", getBuilderScopeName(packageName)).apply {
            defaultValue("{}")
        }.build()
}

typealias FactoryFunctions = List<FactoryFunction>

/**
 * This collects all [FactoryFunctions][FactoryFunctions] of a type.
 */
object BuilderFunctionCollector : KSDefaultVisitor<FactoryFunctions, FactoryFunctions>() {
    override fun defaultHandler(node: KSNode, data: FactoryFunctions): FactoryFunctions =
        emptyList()

    override fun visitFunctionDeclaration(
        function: KSFunctionDeclaration,
        data: FactoryFunctions
    ): FactoryFunctions {
        if (!function.isPublic()) return data
        val name = function.simpleName.asString()
        val hasBuilder = name != "get"
        val specType = function
            .parentDeclaration!! // companion object
            .parentDeclaration!! as KSClassDeclaration // spec
        val specialName =
            function.simpleName.asString().dropLast("builder".length)
                .takeIf { hasBuilder }
                ?.ifBlank { null }

        return if (name.matches(factoryFunctionRegex)) {
            data + FactoryFunction(hasBuilder, function, specType, specialName)
        } else {
            data
        }
    }
}

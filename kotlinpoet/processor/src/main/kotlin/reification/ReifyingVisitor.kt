package dev.kord.codegen.generator.reification

import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.isConstructor
import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.toTypeParameterResolver
import com.squareup.kotlinpoet.ksp.writeTo
import dev.kord.codegen.generator.packageName
import dev.kord.codegen.generator.utils.getDeclaredFunctions
import dev.kord.codegen.generator.visitors.VisitorBase

/**
 * Representation of a function which might be reifiable.
 *
 * @property generateReified whether to reify this overload
 * @param declaration the [KSFunctionDeclaration] which might be reifyable
 * @property bounds the bounds of this functions reified type, if available
 *
 * @see KSFunctionDeclaration
 */
data class MaybeReifiableFunction(
    val generateReified: Boolean,
    private val declaration: KSFunctionDeclaration,
    val bounds: TypeName?
) : KSFunctionDeclaration by declaration

private val KSDeclarationContainer.simpleName: String
    get() = when (this) {
        is KSFile -> fileName.dropLast(3)
        is KSClassDeclaration -> simpleName.asString()
        else -> error("Unexpected type: $this")
    }

/**
 * This visitor detects reifiable functions.
 */
object ReifyingVisitor : VisitorBase() {
    override fun visitFile(file: KSFile, data: SymbolProcessorEnvironment) {
        visitContainer(file, data)
    }

    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: SymbolProcessorEnvironment
    ) = visitContainer(classDeclaration, data)

    private fun visitContainer(
        declarationContainer: KSDeclarationContainer,
        data: SymbolProcessorEnvironment
    ) {
        val ignoredFiles = data.options["ignore-reification"].toString().split(" ")
        val packages = data.options["only-reify"]
        val fileSpec = FileSpec.builder(data.packageName, declarationContainer.simpleName + "Reified").apply {
            (declarationContainer.getDeclaredFunctions() + declarationContainer.declarations
                .filterIsInstance<KSDeclarationContainer>()
                .filter { it is KSClassDeclaration || it is KSFile }
                .filter {
                    val file = it.containingFile ?: it as KSFile
                    file.fileName !in ignoredFiles
                }
                .filter { it is KSFile || (it as KSClassDeclaration).isPublic() }
                .flatMap(KSDeclarationContainer::getDeclaredFunctions))
                .filter { packages == null || it.packageName.asString().startsWith(packages) }
                .filter { it.isReifiable() && !it.isConstructor() && it.isPublic() }
                .process()
                .forEach {
                    addFunction(it.reify(data))
                }
        }.build()

        if (fileSpec.members.isNotEmpty()) {
            fileSpec.writeTo(data.codeGenerator, false)
        }
    }
}

/**
 * Removes the [TypeVariableName.variance] from this [TypeVariableName].
 */
fun TypeVariableName.withoutVariance() =
    TypeVariableName(name, bounds)

/**
 * Removes the [TypeVariableName.variance] or reduces the [WildcardTypeName.inTypes] and [WildcardTypeName.outTypes]
 * to a single [TypeName].
 */
private fun TypeName.withoutVariance(): TypeName {
    return when (this) {
        is WildcardTypeName -> inTypes.firstOrNull() ?: outTypes.firstOrNull() ?: error("STAR variance can't be erased")
        is TypeVariableName -> withoutVariance()
        else -> this
    }
}

private fun KSFunctionDeclaration.findBounds() = parameters.first {
    it.type.resolve().declaration.qualifiedName?.asString() == "kotlin.reflect.KClass"
}.let {
    val argument = it.type.element!!.typeArguments.last()
    return@let if (argument.variance == Variance.STAR) {
        null
    } else {
        val typeResolver = typeParameters.toTypeParameterResolver()
        if (typeResolver.parametersMap.isNotEmpty()) {
            return@let typeResolver.parametersMap.values.last().bounds.first()
        } else {
            // Since the bounds can come from type parameters, they can have an in/out variance
            // Which cannot be used by type variables, therefore we need to ignore it
            argument.toTypeName(typeResolver).withoutVariance()
        }
    }
}

private fun Sequence<KSFunctionDeclaration>.process(): List<MaybeReifiableFunction> {
    return groupBy { it.simpleName.asString() }
        .map { (_, functions) ->
            // TypeName has priority over all, since it stores the most information
            val hasTypeName = functions.any { it.isReifiable(includeKClass = false, includeClassName = false) }
            // ClassName has priority over KClass, since usually KClass overloads call asClassName
            val hasClassName = functions.any { it.isReifiable(includeClassName = true, includeKClass = false) }
            // however, the KClass overload often gives insight about the bounds
            val bounds = functions.firstOrNull { it.isReifiable(includeClassName = false, includeTypeName = false) }
                ?.findBounds()

            functions.map {
                val isTypeName = it.isReifiable(includeKClass = false, includeClassName = false)
                val isClassName = it.isReifiable(includeClassName = true, includeKClass = false)

                val reify = when {
                    hasTypeName -> isTypeName
                    hasClassName -> isClassName
                    else -> true
                }

                MaybeReifiableFunction(reify, it, bounds)
            }
                .filter(MaybeReifiableFunction::generateReified)
        }
        .flatten()
}

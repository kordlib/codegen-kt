package dev.kord.codegen.generator.reification

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.Modifier
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.toTypeParameterResolver
import com.squareup.kotlinpoet.ksp.toTypeVariableName
import dev.kord.codegen.generator.utils.*
import dev.kord.codegen.kotlinpoet.CodeBlock
import dev.kord.codegen.kotlinpoet.FunSpec
import kotlin.reflect.KClass

val AS_CLASS_NAME = MemberName("com.squareup.kotlinpoet", "asClassName")
val TYPE_NAME_OF = MemberName("com.squareup.kotlinpoet", "typeNameOf")

/**
 * Attempts to reify a [MaybeReifiableFunction] with the following steps:
 *
 * - Add [inline modifier][KModifier.INLINE]
 * - determine receiver type
 *     - If original function is part of a class:
 *         - Add all type parameters of that class
 *         - Add that class as receiver (and parameterize it if needed)
 *     - otherwise add the receiver type of the original function
 *  - Inherit all the functions parameters
 *      - drop the reifiable parameters
 *      - Mark parameter functions with `noinline` if the parent function is not inlined
 *  - Add reified type parameters
 *  - Add code calling the original function
 */
fun MaybeReifiableFunction.reify(): FunSpec {
    val nameAllocator = NameAllocator()

    return FunSpec(simpleName.asString()) {
        addModifiers(KModifier.INLINE)
        // If the parent of the function is a class,
        // we need to define the reified function as an extension of that class
        // since we do not own the class
        if (parentDeclaration is KSClassDeclaration) {
            // If that class has type parameters, we need to add those to the extension function
            if (parentDeclaration.typeParameters.isNotEmpty()) {
                val variables = parentDeclaration.typeParameters.map {
                    it.toTypeVariableName(parentDeclaration.typeParameters.toTypeParameterResolver())
                        .withoutVariance().also { typeVariable ->
                            TypeVariableName(nameAllocator.newName(typeVariable.name), bounds)
                        }
                }
                addTypeVariables(variables)
                // Then parameterize the type by the type variables
                // e.g., public inline fun <T : Annotatable.Builder<T>> Annotatable.Builder<T>.x()
                receiver(parentDeclaration.toClassName().parameterizedBy(variables))
            } else {
                receiver(parentDeclaration.toClassName())
            }
        } else if (extensionReceiver != null) {
            // otherwise, just use the receiver of the actual function
            if (!extensionReceiver.resolve().isError) {
                receiver(extensionReceiver.toTypeName())
            }
        }

        val typeVariableResolver = typeVariables.toTypeParameterResolver()
        returns(returnType!!.toTypeName(typeVariableResolver))
        val originalParameters = this@reify.parameters
            .asSequence()
            .map {
                it.toParameterSpec(
                    typeVariableResolver,
                    simpleName.asString().endsWith("Builder"),
                    Modifier.INLINE !in this@reify.modifiers
                )
            }
            .toList()

        val normalParameters = originalParameters
            .filterNot { KModifier.VARARG !in it.modifiers && it.isReifiable() }

        addParameters(normalParameters)

        val valueParameters = originalParameters.map {
            if (KModifier.VARARG !in it.modifiers && it.isReifiable()) {
                val typeVariableName = nameAllocator.newName(it.name.first().uppercase())
                val typeVariable =
                    TypeVariableName(typeVariableName, bounds).copy(reified = true)
                addTypeVariable(typeVariable)
                when (((it.type as? ParameterizedTypeName)?.rawType ?: it.type).copy(nullable = false)) {
                    KClass::class.asClassName() ->
                        CodeBlock.of("%L::class", typeVariable)

                    ClassName::class.asClassName() -> CodeBlock.of("%L::class.%M()", typeVariable, AS_CLASS_NAME)
                    TypeName::class.asClassName() -> CodeBlock.of("%M<%L>()", TYPE_NAME_OF, typeVariable)
                    else -> error("Unreifiable type: ${it.type}")
                }
            } else {
                CodeBlock {
                    if (KModifier.VARARG in it.modifiers) {
                        add("%N·=·", it)
                    }
                    add("%N", it)
                }
            }
        }.joinToCode(", ")

        addAnnotationsFromFunction(this@reify)
        val builderParameter = this@reify.parameters.firstOrNull { it.type.isCallableType() }
        if (builderParameter != null && Modifier.INLINE in this@reify.modifiers) {
            addCallsInPlaceExactlyOnce(ParameterSpec(builderParameter.name!!.asString(), NOTHING))
        }
        addCode("return·%N(%L)", simpleName.asString(), valueParameters)
    }
}

@PublishedApi
internal fun TypeVariableName(name: String, bound: TypeName? = null) = if (bound == null) {
    TypeVariableName.invoke(name)
} else {
    TypeVariableName.invoke(name, bound)
}

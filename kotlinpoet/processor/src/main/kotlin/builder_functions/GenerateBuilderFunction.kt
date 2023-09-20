package dev.kord.codegen.generator.builder_functions

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.Modifier
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toKModifier
import dev.kord.codegen.generator.packageName
import dev.kord.codegen.generator.utils.addAnnotationsFromFunction
import dev.kord.codegen.generator.utils.addCallsInPlaceExactlyOnce
import dev.kord.codegen.generator.utils.mapToValueParameterList
import dev.kord.codegen.generator.utils.toParameterSpec
import dev.kord.codegen.kotlinpoet.FunSpec

/**
 * ```kotlin
 * public fun Spec(originalParameters): Spec = `get`(originalParameters)
 * ```
 */
fun FactoryFunction.generateNoBuilderFactoryFunction(): FunSpec {
    val className = specType.toClassName()
    return generateFactoryFunction {
        val valueParameters = parameters.mapToValueParameterList()

        addCode("""return·%M(%L)""", className.nestedClass("Companion").member("get"), valueParameters)
    }
}

/**
 * ```kotlin
 * public inline fun Spec(originalParameters, block: CodeBlockBuilderScope = {}): Spec {
 *     contract { callsInPlace(block, EXACTLY_ONCE) }
 *     return Spec.builder(originalParameters).apply(block).build()
 * }
 * ```
 */
fun FactoryFunction.generateBuilderFactoryFunction(environment: SymbolProcessorEnvironment): FunSpec {
    return generateFactoryFunction {
        if (specialName != null) {
            receiver(specType.toClassName().nestedClass("Companion"))
        }
        addModifiers(KModifier.INLINE)

        val builderParameter = getBuilderParameter(environment.packageName)
        addParameter(builderParameter)

        // These are the parameters, we pass to the kotlinpoet builder function
        val valueParameters = parameters
            .dropLast(1) // last is builder
            .mapToValueParameterList()

        addCallsInPlaceExactlyOnce(builderParameter)
        addCode(
            "return·%T.%L(%L).apply(%N).build()",
            specType.toClassName(),
            simpleName.asString(),
            valueParameters,
            builderParameter
        )
    }
}

private fun FactoryFunction.generateFactoryFunction(additional: FunSpec.Builder.() -> Unit): FunSpec {
    return FunSpec(builderFunctionName) {
        addOriginatingKSFile(specType.containingFile!!)
        returns(specType.toClassName())
        addModifiers(this@generateFactoryFunction.modifiers.mapNotNull(Modifier::toKModifier))
        addParameters(this@generateFactoryFunction.parameters.map(KSValueParameter::toParameterSpec))
        addAnnotationsFromFunction(this@generateFactoryFunction)
        additional()
    }
}

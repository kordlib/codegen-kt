@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package dev.kord.codegen.generator.constructor_inliner

import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import dev.kord.codegen.generator.utils.*
import dev.kord.codegen.generator.builder_functions.FactoryFunction

context(FactoryFunction)
private fun FunSpec.Builder.getValueParametersList(packageName: String): CodeBlock {
    val allParameters = if (hasBuilder) {
        parameters + getBuilderParameter(packageName)
    } else {
        parameters
    }

    return allParameters.mapToValueParameterList()
}

/**
 * ```kotlin
 * public fun OriginalBuilderType.addProperty(
 *   originalParameters,
 *   block: SpecBuilderScope = {},
 * ): SubSpecDelegateProvider<Spec> {
 *   contract { callsInPlace(block, EXACTLY_ONCE) }
 *   return Spec(originalParameters, block).also(::addSpec)
 * }
 */
fun FactoryFunction.generateInlinedConstructorWithNameParameter(
    packageName: String,
    constructor: InlineConstructor
): FunSpec = generateInlinedConstructor(packageName, constructor) {
    addParameters(this@generateInlinedConstructorWithNameParameter.parameters.map(KSValueParameter::toParameterSpec))
    returns(specType.toClassName())
    addCode(
        "return·%L(%L).also(::%L)",
        builderFunctionName.takeIf { !constructor.useQualifiedName }
            ?.escapeIfNecessary()
            ?: "$packageName.$builderFunctionName",
        getValueParametersList(packageName),
        constructor.functionName
    )
}

/**
 * ```kotlin
 * public fun OriginalBuilderType.addProperty(
 *   originalParameters,
 *   block: SpecBuilderScope = {},
 * ): SubSpecDelegateProvider<Spec> {
 *   contract { callsInPlace(block, EXACTLY_ONCE) }
 *   return produceByName { name ->
 *     Spec(name, originalParameters, block).also(::addSpec)
 *   }
 * }
 */
fun FactoryFunction.generateInlinedConstructorWithNameDelegate(
    packageName: String,
    constructor: InlineConstructor
): FunSpec = generateInlinedConstructor(packageName, constructor, noinline = true) {
    val valueParameters = this@generateInlinedConstructorWithNameDelegate.parameters
        .map(KSValueParameter::toParameterSpec)
    addParameters(valueParameters)
    val valueParameterList = getValueParametersList(packageName)
    parameters.removeIf {
        it.name == constructor.nameProperty
    }
    returns(SUB_SPEC_DELEGATE_PROVIDER.parameterizedBy(specType.toClassName()))

    val code = buildCodeBlock {
        beginControlFlow("return·%M { %L ->", PRODUCE_BY_NAME, constructor.nameProperty)
        addStatement(
            "%L(%L).also(::%L)",
            builderFunctionName.takeIf { !constructor.useQualifiedName }
                ?.escapeIfNecessary()
                ?: "$packageName.$builderFunctionName",
            valueParameterList,
            constructor.functionName,
        )
        endControlFlow()
    }

    addCode(code)
}

private fun FactoryFunction.generateInlinedConstructor(
    packageName: String,
    constructor: InlineConstructor,
    noinline: Boolean = false,
    additional: FunSpec.Builder.() -> Unit
): FunSpec {
    val name = if (constructor.functionName.startsWith("add")) {
        specialName?.let { "add${it.replaceFirstChar(Char::uppercase)}" }
            ?: constructor.functionName
    } else {
        constructor.functionName
    }.run {
        constructor.nameMapping.fold(this) { acc, mapping ->
            acc.replace(mapping.originalName, mapping.newName)
        }
    }
    val builderParameter = getBuilderParameter(packageName)

    return FunSpec.builder(name).apply {
        receiver(constructor.forClass.toClassName())

        if (hasBuilder) {
            if (!noinline) {
                addModifiers(KModifier.INLINE)
            }
            addCallsInPlaceExactlyOnce(builderParameter)
        }

        addAnnotationsFromFunction(this@generateInlinedConstructor)
        additional()
        if (hasBuilder) {
            addParameter(builderParameter)
        }
    }.build()
}

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.AnnotationSpec.Companion.`get`
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.ParameterizedTypeName
import javax.lang.model.element.AnnotationMirror
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass

public typealias AnnotationSpecBuilderScope = @CodeGenDsl AnnotationSpec.Builder.() -> Unit

@DelicateKotlinPoetApi(message = "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun AnnotationSpec(`annotation`: Annotation, includeDefaultValues: Boolean = false): AnnotationSpec = `get`(`annotation`, includeDefaultValues)

@DelicateKotlinPoetApi(message = "Mirror APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun AnnotationSpec(`annotation`: AnnotationMirror): AnnotationSpec = `get`(`annotation`)

public inline fun AnnotationSpec(type: ClassName, block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return AnnotationSpec.builder(type).apply(block).build()
}

public inline fun AnnotationSpec(type: ParameterizedTypeName, block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return AnnotationSpec.builder(type).apply(block).build()
}

@DelicateKotlinPoetApi(message = "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public inline fun AnnotationSpec(type: Class<out Annotation>, block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return AnnotationSpec.builder(type).apply(block).build()
}

public inline fun AnnotationSpec(type: KClass<out Annotation>, block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    return AnnotationSpec.builder(type).apply(block).build()
}

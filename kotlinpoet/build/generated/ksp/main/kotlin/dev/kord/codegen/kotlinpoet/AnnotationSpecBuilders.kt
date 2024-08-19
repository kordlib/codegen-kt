package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.Annotatable
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.ParameterizedTypeName
import javax.lang.model.element.AnnotationMirror
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass

public inline fun Annotatable.Builder<*>.addAnnotation(type: ClassName,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

public inline fun Annotatable.Builder<*>.addAnnotation(type: ParameterizedTypeName,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

@DelicateKotlinPoetApi(message =
    "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public inline fun Annotatable.Builder<*>.addAnnotation(type: Class<out Annotation>,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

public inline fun Annotatable.Builder<*>.addAnnotation(type: KClass<out Annotation>,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

@DelicateKotlinPoetApi(message =
    "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun Annotatable.Builder<*>.addAnnotation(`annotation`: Annotation,
    includeDefaultValues: Boolean = false): AnnotationSpec = AnnotationSpec(`annotation`,
    includeDefaultValues).also(::addAnnotation)

@DelicateKotlinPoetApi(message =
    "Mirror APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun Annotatable.Builder<*>.addAnnotation(`annotation`: AnnotationMirror): AnnotationSpec =
    AnnotationSpec(`annotation`).also(::addAnnotation)

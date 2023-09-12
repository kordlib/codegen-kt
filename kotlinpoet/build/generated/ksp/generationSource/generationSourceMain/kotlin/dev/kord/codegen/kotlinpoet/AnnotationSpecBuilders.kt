@file:OptIn(ExperimentalContracts::class)

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeSpec
import javax.lang.model.element.AnnotationMirror
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.reflect.KClass

@DelicateKotlinPoetApi(message =
    "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun TypeSpec.Builder.addAnnotation(`annotation`: Annotation, includeDefaultValues: Boolean =
    false): AnnotationSpec = AnnotationSpec(`annotation`,
    includeDefaultValues).also(::addAnnotation)

@DelicateKotlinPoetApi(message =
    "Mirror APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun TypeSpec.Builder.addAnnotation(`annotation`: AnnotationMirror): AnnotationSpec =
    AnnotationSpec(`annotation`).also(::addAnnotation)

public inline fun TypeSpec.Builder.addAnnotation(type: ClassName, block: AnnotationSpecBuilderScope
    = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

public inline fun TypeSpec.Builder.addAnnotation(type: ParameterizedTypeName,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

@DelicateKotlinPoetApi(message =
    "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public inline fun TypeSpec.Builder.addAnnotation(type: Class<out Annotation>,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

public inline fun TypeSpec.Builder.addAnnotation(type: KClass<out Annotation>,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

@DelicateKotlinPoetApi(message =
    "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun FileSpec.Builder.addAnnotation(`annotation`: Annotation, includeDefaultValues: Boolean =
    false): AnnotationSpec = AnnotationSpec(`annotation`,
    includeDefaultValues).also(::addAnnotation)

@DelicateKotlinPoetApi(message =
    "Mirror APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public fun FileSpec.Builder.addAnnotation(`annotation`: AnnotationMirror): AnnotationSpec =
    AnnotationSpec(`annotation`).also(::addAnnotation)

public inline fun FileSpec.Builder.addAnnotation(type: ClassName, block: AnnotationSpecBuilderScope
    = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

public inline fun FileSpec.Builder.addAnnotation(type: ParameterizedTypeName,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

@DelicateKotlinPoetApi(message =
    "Java reflection APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
public inline fun FileSpec.Builder.addAnnotation(type: Class<out Annotation>,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

public inline fun FileSpec.Builder.addAnnotation(type: KClass<out Annotation>,
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return AnnotationSpec(type, block).also(::addAnnotation)
}

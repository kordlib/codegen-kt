package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.Annotatable
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.Annotation
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

public inline fun <reified T : Annotation> Annotatable.Builder<*>.addAnnotation(block: AnnotationSpecBuilderScope = {}): AnnotationSpec {
  contract { callsInPlace(block, EXACTLY_ONCE) }
  return addAnnotation(T::class.asClassName(), block)
}

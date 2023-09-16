package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.Annotatable
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.Annotation

public inline fun <reified T : Annotation>
    Annotatable.Builder<*>.addAnnotation(block: AnnotationSpecBuilderScope = {}): AnnotationSpec =
    addAnnotation(T::class.asClassName(), block)

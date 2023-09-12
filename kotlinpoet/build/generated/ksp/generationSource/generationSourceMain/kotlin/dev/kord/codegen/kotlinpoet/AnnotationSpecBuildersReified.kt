package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.Annotation

public inline fun <reified T : Annotation> TypeSpec.Builder.addAnnotation(noinline
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec = addAnnotation(T::class.asClassName(),
    block)

public inline fun <reified T : Annotation> FileSpec.Builder.addAnnotation(noinline
    block: AnnotationSpecBuilderScope = {}): AnnotationSpec = addAnnotation(T::class.asClassName(),
    block)

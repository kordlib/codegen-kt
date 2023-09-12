package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.Annotation

public inline fun <reified T : Annotation> AnnotationSpec.Companion.builder():
    AnnotationSpec.Builder = builder(T::class.asClassName())

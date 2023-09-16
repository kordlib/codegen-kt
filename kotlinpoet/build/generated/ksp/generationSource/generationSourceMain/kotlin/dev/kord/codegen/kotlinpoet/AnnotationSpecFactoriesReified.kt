package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.Annotation

public inline fun <reified T : Annotation> AnnotationSpec(block: AnnotationSpecBuilderScope = {}):
    AnnotationSpec = AnnotationSpec(T::class.asClassName(), block)

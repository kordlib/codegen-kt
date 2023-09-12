package dev.kord.codegen.ksp.processor

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member

const val PROCESSOR_ANNOTATION = "dev.kord.codegen.ksp.annotations.ProcessorAnnotation"
const val NULL_IF_DEFAULT = "dev.kord.codegen.ksp.annotations.NullIfDefault"

val ARGUMENTS = ClassName("dev.kord.codegen.ksp.annotations", "AnnotationArguments", "Companion")
    .member("arguments")
val ARGUMENTS_NOT_NULL = ClassName("dev.kord.codegen.ksp.annotations", "AnnotationArguments", "NonNullAnnotationArguments", "Companion")
    .member("notNull")

val GET_ANNOTATIONS_BY_TYPE = MemberName("dev.kord.codegen.ksp", "getAnnotationsByType")
val GET_ANNOTATION_BY_TYPE = MemberName("dev.kord.codegen.ksp", "getAnnotationByType")

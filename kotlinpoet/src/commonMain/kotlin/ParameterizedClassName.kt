package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.typeNameOf

/**
 * Parameterizes this [ClassName] by [T].
 */
public inline fun <reified T : Any> ClassName.parameterizedBy(): ParameterizedTypeName = parameterizedBy(typeNameOf<T>())

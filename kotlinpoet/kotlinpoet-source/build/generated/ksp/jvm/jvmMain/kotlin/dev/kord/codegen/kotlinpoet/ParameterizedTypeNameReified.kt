package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.typeNameOf

public inline fun <reified T> ParameterizedTypeName.plusParameter(): ParameterizedTypeName = plusParameter(typeNameOf<T>())

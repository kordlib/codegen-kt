package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ExperimentalKotlinPoetApi
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.collections.List

@ExperimentalKotlinPoetApi
public inline fun <reified R, reified R_> LambdaTypeName(
  parameters: List<ParameterSpec> = emptyList(),
  contextReceivers: List<TypeName> = emptyList(),
  contextParameters: List<TypeName> = emptyList(),
): LambdaTypeName = LambdaTypeName(typeNameOf<R>(), parameters, typeNameOf<R_>(), contextReceivers, contextParameters)

public inline fun <reified R, reified R_> LambdaTypeName(parameters: List<ParameterSpec> = emptyList()): LambdaTypeName = LambdaTypeName(typeNameOf<R>(), parameters, typeNameOf<R_>())

public inline fun <reified R, reified R_> LambdaTypeName(vararg parameters: TypeName = emptyArray()): LambdaTypeName = LambdaTypeName(typeNameOf<R>(), parameters = parameters, typeNameOf<R_>())

public inline fun <reified R, reified R_> LambdaTypeName(vararg parameters: ParameterSpec = emptyArray()): LambdaTypeName = LambdaTypeName(typeNameOf<R>(), parameters = parameters, typeNameOf<R_>())

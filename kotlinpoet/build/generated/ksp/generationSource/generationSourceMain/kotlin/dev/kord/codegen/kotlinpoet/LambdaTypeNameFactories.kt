package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ExperimentalKotlinPoetApi
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.LambdaTypeName.Companion.`get`
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName

@ExperimentalKotlinPoetApi
public fun LambdaTypeName(
    `receiver`: TypeName? = null,
    parameters: List<ParameterSpec> = emptyList(),
    returnType: TypeName,
    contextReceivers: List<TypeName> = emptyList(),
): LambdaTypeName = `get`(`receiver`, parameters, returnType, contextReceivers)

public fun LambdaTypeName(
    `receiver`: TypeName? = null,
    parameters: List<ParameterSpec> = emptyList(),
    returnType: TypeName,
): LambdaTypeName = `get`(`receiver`, parameters, returnType)

public fun LambdaTypeName(
    `receiver`: TypeName? = null,
    vararg parameters: TypeName = emptyArray(),
    returnType: TypeName,
): LambdaTypeName = `get`(`receiver`, parameters = parameters, returnType)

public fun LambdaTypeName(
    `receiver`: TypeName? = null,
    vararg parameters: ParameterSpec = emptyArray(),
    returnType: TypeName,
): LambdaTypeName = `get`(`receiver`, parameters = parameters, returnType)

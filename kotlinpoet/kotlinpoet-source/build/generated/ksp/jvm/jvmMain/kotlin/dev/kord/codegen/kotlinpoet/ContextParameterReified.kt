package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ContextParameterizable
import com.squareup.kotlinpoet.ExperimentalKotlinPoetApi
import com.squareup.kotlinpoet.typeNameOf
import kotlin.String

@ExperimentalKotlinPoetApi
public inline fun <T : ContextParameterizable.Builder<T>, reified T_> ContextParameterizable.Builder<T>.contextParameter(name: String): T = contextParameter(name, typeNameOf<T_>())

@ExperimentalKotlinPoetApi
public inline fun <T : ContextParameterizable.Builder<T>, reified T_> ContextParameterizable.Builder<T>.contextParameter(): T = contextParameter(typeNameOf<T_>())

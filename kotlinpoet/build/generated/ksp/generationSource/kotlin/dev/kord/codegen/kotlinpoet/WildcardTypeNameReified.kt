package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.typeNameOf

public inline fun <reified O> WildcardTypeName.Companion.producerOf(): WildcardTypeName = producerOf(typeNameOf<O>())

public inline fun <reified I> WildcardTypeName.Companion.consumerOf(): WildcardTypeName = consumerOf(typeNameOf<I>())

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import kotlin.Int

public inline fun <reified O> ClassName.compareTo(): Int = compareTo(O::class.asClassName())

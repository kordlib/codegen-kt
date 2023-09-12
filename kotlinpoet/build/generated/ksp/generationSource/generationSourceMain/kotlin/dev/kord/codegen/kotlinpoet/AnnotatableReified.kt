package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.Annotatable
import com.squareup.kotlinpoet.asClassName

public inline fun <T : Annotatable.Builder<T>, reified A> Annotatable.Builder<T>.addAnnotation(): T
    = addAnnotation(A::class.asClassName())

package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.Taggable
import kotlin.Any

public inline fun <reified T : Any> Taggable.tag(): T? = tag(T::class)

public inline fun <T : Taggable.Builder<T>, reified T_ : Any> Taggable.Builder<T>.tag(tag: Any?): T = tag(T_::class, tag)

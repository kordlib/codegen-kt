package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.String
import kotlin.collections.Iterable

public inline fun <reified C> FileSpec.Builder.addImport(vararg names: String): FileSpec.Builder = addImport(C::class.asClassName(), names = names)

public inline fun <reified C> FileSpec.Builder.addImport(names: Iterable<String>): FileSpec.Builder = addImport(C::class.asClassName(), names)

public inline fun <reified C> FileSpec.Builder.addAliasedImport(`as`: String): FileSpec.Builder = addAliasedImport(C::class.asClassName(), `as`)

public inline fun <reified C> FileSpec.Builder.addAliasedImport(memberName: String, `as`: String): FileSpec.Builder = addAliasedImport(C::class.asClassName(), memberName, `as`)

public inline fun <reified A> FileSpec.Builder.addAnnotation(): FileSpec.Builder = addAnnotation(A::class.asClassName())

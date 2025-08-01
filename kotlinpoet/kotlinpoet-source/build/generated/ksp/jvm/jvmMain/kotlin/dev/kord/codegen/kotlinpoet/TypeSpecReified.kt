package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.typeNameOf
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.ReplaceWith
import kotlin.String
import kotlin.Suppress

public inline fun <reified S> TypeSpec.Builder.superclass(): TypeSpec.Builder = superclass(typeNameOf<S>())

public inline fun <reified S> TypeSpec.Builder.addSuperinterface(`delegate`: CodeBlock = emptyCodeBlock()): TypeSpec.Builder = addSuperinterface(typeNameOf<S>(), `delegate`)

public inline fun <reified S> TypeSpec.Builder.addSuperinterface(constructorParameter: String): TypeSpec.Builder = addSuperinterface(typeNameOf<S>(), constructorParameter)

public inline fun <reified A> TypeSpec.Builder.addAnnotation(): TypeSpec.Builder = addAnnotation(A::class.asClassName())

public inline fun <reified C> TypeSpec.Companion.classBuilder(): TypeSpec.Builder = classBuilder(C::class.asClassName())

@Deprecated(
  message = "Use classBuilder() instead. This function will be removed in KotlinPoet 2.0.",
  replaceWith = ReplaceWith(expression = "TypeSpec.classBuilder(className).addModifiers(KModifier.EXPECT)"),
  level = DeprecationLevel.WARNING,
)
@Suppress(names = arrayOf("DEPRECATION"))
public inline fun <reified C> TypeSpec.Companion.expectClassBuilder(): TypeSpec.Builder = expectClassBuilder(C::class.asClassName())

public inline fun <reified C> TypeSpec.Companion.objectBuilder(): TypeSpec.Builder = objectBuilder(C::class.asClassName())

public inline fun <reified C> TypeSpec.Companion.interfaceBuilder(): TypeSpec.Builder = interfaceBuilder(C::class.asClassName())

public inline fun <reified C> TypeSpec.Companion.funInterfaceBuilder(): TypeSpec.Builder = funInterfaceBuilder(C::class.asClassName())

public inline fun <reified C> TypeSpec.Companion.enumBuilder(): TypeSpec.Builder = enumBuilder(C::class.asClassName())

public inline fun <reified C> TypeSpec.Companion.annotationBuilder(): TypeSpec.Builder = annotationBuilder(C::class.asClassName())

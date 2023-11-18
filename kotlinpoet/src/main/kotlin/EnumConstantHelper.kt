package dev.kord.codegen.kotlinpoet

import com.squareup.kotlinpoet.TypeSpec

/**
 * Adds an enum constant with [name] to this [TypeSpec].
 */
public fun TypeSpec.Builder.addEnumConstant(name: String, builder: TypeSpecBuilderScope = {}): TypeSpec.Builder =
    addEnumConstant(name, TypeSpec.anonymousClass(builder))

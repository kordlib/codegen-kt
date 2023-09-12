@file:InlineConstructor(
    forClass = TypeSpec.Builder::class,
    functionName = "addFunction",
    ignoreBuilders = ["getterBuilder", "setterBuilder"],
)

@file:InlineConstructor(
    forClass = FileSpec.Builder::class,
    functionName = "addFunction",
    ignoreBuilders = ["getterBuilder", "setterBuilder", "constructorBuilder"]
)

package dev.kord.codegen.kotlinpoet.builders

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.kord.codegen.ksp.annotations.InlineConstructor


@file:InlineConstructor(
    forClass = FunSpec.Builder::class,
    functionName = "addCode"
)

@file:InlineConstructor(
    forClass = FileSpec.Builder::class,
    functionName = "addCode"
)

@file:InlineConstructor(
    forClass = TypeSpec.Builder::class,
    functionName = "addCode"
)

@file:InlineConstructor(
    forClass = TypeSpec.Builder::class,
    functionName = "addInitializerBlock"
)

@file:InlineConstructor(
    forClass = PropertySpec.Builder::class,
    functionName = "delegate"
)

package dev.kord.codegen.kotlinpoet.builders

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.kord.codegen.ksp.annotations.InlineConstructor

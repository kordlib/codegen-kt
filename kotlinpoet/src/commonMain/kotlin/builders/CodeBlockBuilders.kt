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

@file:InlineConstructor(
    forClass = Documentable.Builder::class,
    functionName = "addKdoc"
)

package dev.kord.codegen.kotlinpoet.builders

import com.squareup.kotlinpoet.*
import dev.kord.codegen.ksp.annotations.InlineConstructor

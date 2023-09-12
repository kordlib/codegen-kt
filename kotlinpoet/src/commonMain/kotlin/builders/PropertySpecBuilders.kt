@file:InlineConstructor(
    forClass = TypeSpec.Builder::class,
    functionName = "addProperty",
    nameProperty = "name"
)

@file:InlineConstructor(
    forClass = FileSpec.Builder::class,
    functionName = "addProperty",
    nameProperty = "name"
)

package dev.kord.codegen.kotlinpoet.builders

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.kord.codegen.ksp.annotations.InlineConstructor

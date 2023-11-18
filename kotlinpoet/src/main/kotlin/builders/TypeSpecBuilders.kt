@file:InlineConstructor(
    forClass = TypeSpecHolder.Builder::class,
    functionName = "addType",
    nameMapping = [
        InlineConstructor.NameMapping("Annotation", "AnnotationClass")
    ],
    ignoreBuilders = ["anonymousClassBuilder"]
)

@file:InlineConstructor(
    forClass = TypeSpec.Builder::class,
    functionName = "primaryConstructor",
    ignoreBuilders = ["getterBuilder", "setterBuilder", "builder"]
)

package dev.kord.codegen.kotlinpoet.builders

import com.squareup.kotlinpoet.TypeSpecHolder
import com.squareup.kotlinpoet.TypeSpec
import dev.kord.codegen.ksp.annotations.InlineConstructor

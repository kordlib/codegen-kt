@file:InlineConstructor(
    forClass = TypeSpec.Builder::class,
    functionName = "addFunction",
    ignoreBuilders = ["getterBuilder", "setterBuilder", "builder"],
)

@file:InlineConstructor(
    forClass = MemberSpecHolder.Builder::class,
    functionName = "addFunction",
    ignoreBuilders = ["getterBuilder", "setterBuilder", "constructorBuilder"]
)

package dev.kord.codegen.kotlinpoet.builders

import com.squareup.kotlinpoet.MemberSpecHolder
import com.squareup.kotlinpoet.TypeSpec
import dev.kord.codegen.ksp.annotations.InlineConstructor

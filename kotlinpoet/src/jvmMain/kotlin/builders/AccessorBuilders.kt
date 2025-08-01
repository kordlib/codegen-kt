@file:InlineConstructor(
    forClass = PropertySpec.Builder::class,
    functionName = "getter",
    nameMapping = [InlineConstructor.NameMapping("addGetter", "getter")],
    ignoreBuilders = ["builder", "constructorBuilder", "setterBuilder"],
    useQualifiedName = true
)

@file:InlineConstructor(
    forClass = PropertySpec.Builder::class,
    functionName = "setter",
    nameMapping = [InlineConstructor.NameMapping("addSetter", "setter")],
    ignoreBuilders = ["builder", "constructorBuilder", "getterBuilder"],
    useQualifiedName = true
)

package dev.kord.codegen.kotlinpoet.builders

import com.squareup.kotlinpoet.PropertySpec
import dev.kord.codegen.ksp.annotations.InlineConstructor

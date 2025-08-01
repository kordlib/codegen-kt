package dev.kord.codegen.kotlinpoet.js

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeAliasSpec
import com.squareup.kotlinpoet.TypeSpec
import dev.kord.codegen.kotlinpoet.addAnnotation

/**
 * Adds `ExperimentalJsStatic` to this [FileSpec]
 */
public fun FileSpec.Builder.experimentalJsStatic() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsStatic"))
}

/**
 * Adds `ExperimentalJsStatic` to this [FunSpec]
 */
public fun FunSpec.Builder.experimentalJsStatic() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsStatic"))
}

/**
 * Adds `ExperimentalJsStatic` to this [ParameterSpec]
 */
public fun ParameterSpec.Builder.experimentalJsStatic() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsStatic"))
}

/**
 * Adds `ExperimentalJsStatic` to this [TypeSpec]
 */
public fun TypeSpec.Builder.experimentalJsStatic() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsStatic"))
}

/**
 * Adds `ExperimentalJsStatic` to this [TypeAliasSpec]
 */
public fun TypeAliasSpec.Builder.experimentalJsStatic() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsStatic"))
}

/**
 * Adds `ExperimentalJsStatic` to this [PropertySpec]
 */
public fun PropertySpec.Builder.experimentalJsStatic() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsStatic"))
}

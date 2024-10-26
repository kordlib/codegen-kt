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

/**
 * Adds `ExperimentalJsReflectionCreateInstance` to this [FunSpec]
 */
public fun FunSpec.Builder.experimentalJsReflectionCreateInstance() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsReflectionCreateInstance"))
}

/**
 * Adds `ExperimentalJsReflectionCreateInstance` to this [ParameterSpec]
 */
public fun ParameterSpec.Builder.experimentalJsReflectionCreateInstance() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsReflectionCreateInstance"))
}

/**
 * Adds `ExperimentalJsReflectionCreateInstance` to this [TypeSpec]
 */
public fun TypeSpec.Builder.experimentalJsReflectionCreateInstance() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsReflectionCreateInstance"))
}

/**
 * Adds `ExperimentalJsReflectionCreateInstance` to this [TypeAliasSpec]
 */
public fun TypeAliasSpec.Builder.experimentalJsReflectionCreateInstance() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsReflectionCreateInstance"))
}

/**
 * Adds `ExperimentalJsReflectionCreateInstance` to this [PropertySpec]
 */
public fun PropertySpec.Builder.experimentalJsReflectionCreateInstance() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsReflectionCreateInstance"))
}

/**
 * Adds `ExperimentalJsCollectionsApi` to this [FunSpec]
 */
public fun FunSpec.Builder.experimentalJsCollectionsApi() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsCollectionsApi"))
}

/**
 * Adds `ExperimentalJsCollectionsApi` to this [TypeSpec]
 */
public fun TypeSpec.Builder.experimentalJsCollectionsApi() {
    addAnnotation(ClassName("kotlin.js", "ExperimentalJsCollectionsApi"))
}

/**
 * Adds `JsName` to this [FunSpec]
 */
public fun FunSpec.Builder.jsName(name: String) {
    addAnnotation(ClassName("kotlin.js", "JsName")) {
        addMember("%S", name)
    }
}

/**
 * Adds `JsName` to this [TypeSpec]
 */
public fun TypeSpec.Builder.jsName(name: String) {
    addAnnotation(ClassName("kotlin.js", "JsName")) {
        addMember("%S", name)
    }
}

/**
 * Adds `JsName` to this [PropertySpec]
 */
public fun PropertySpec.Builder.jsName(name: String) {
    addAnnotation(ClassName("kotlin.js", "JsName")) {
        addMember("%S", name)
    }
}

/**
 * Adds `JsFileName` to this [FileSpec]
 */
public fun FileSpec.Builder.jsFileName(name: String) {
    addAnnotation(ClassName("kotlin.js", "JsFileName")) {
        addMember("%S", name)
    }
}

/**
 * Adds `JsExport` to this [FileSpec]
 */
public fun FileSpec.Builder.jsExport() {
    addAnnotation(ClassName("kotlin.js", "JsExport"))
}

/**
 * Adds `JsExport` to this [FunSpec]
 */
public fun FunSpec.Builder.jsExport() {
    addAnnotation(ClassName("kotlin.js", "JsExport"))
}

/**
 * Adds `JsExport` to this [TypeSpec]
 */
public fun TypeSpec.Builder.jsExport() {
    addAnnotation(ClassName("kotlin.js", "JsExport"))
}

/**
 * Adds `JsExport` to this [PropertySpec]
 */
public fun PropertySpec.Builder.jsExport() {
    addAnnotation(ClassName("kotlin.js", "JsExport"))
}

/**
 * Adds `Ignore` to this [FunSpec]
 */
public fun FunSpec.Builder.ignore() {
    addAnnotation(ClassName("kotlin.js", "JsExport", "Ignore"))
}

/**
 * Adds `Ignore` to this [TypeSpec]
 */
public fun TypeSpec.Builder.ignore() {
    addAnnotation(ClassName("kotlin.js", "JsExport", "Ignore"))
}

/**
 * Adds `Ignore` to this [PropertySpec]
 */
public fun PropertySpec.Builder.ignore() {
    addAnnotation(ClassName("kotlin.js", "JsExport", "Ignore"))
}

/**
 * Adds `JsStatic` to this [FunSpec]
 */
public fun FunSpec.Builder.jsStatic() {
    addAnnotation(ClassName("kotlin.js", "JsStatic"))
}

/**
 * Adds `JsStatic` to this [PropertySpec]
 */
public fun PropertySpec.Builder.jsStatic() {
    addAnnotation(ClassName("kotlin.js", "JsStatic"))
}

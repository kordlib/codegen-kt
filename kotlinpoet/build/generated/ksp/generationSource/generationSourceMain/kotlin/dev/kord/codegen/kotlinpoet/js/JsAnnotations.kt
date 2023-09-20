package dev.kord.codegen.kotlinpoet.js

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.kord.codegen.kotlinpoet.addAnnotation

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

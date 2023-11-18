package dev.kord.codegen.generator.utils

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.toAnnotationSpec
import dev.kord.codegen.kotlinpoet.addAnnotation

/**
 * Adds all the annotations from [function] to this [FunSpec].
 */
@OptIn(DelicateKotlinPoetApi::class)
fun FunSpec.Builder.addAnnotationsFromFunction(function: KSFunctionDeclaration) {
    function.annotations.forEach {
        val declaration = it.annotationType.resolve().declaration
        val name = declaration.simpleName.asString()
        val packageName = declaration.packageName.asString()
        // Do not add the platform annotations like @JvmStatic or @JsName, since they don't apply in most cases
        // Or annotations like @Suppress
        if ((packageName != "kotlin.jvm" && packageName != "kotlin.js" && packageName != "kotlin") || name == "Deprecated") {
            addAnnotation(it.toAnnotationSpec())
        }
        if (name == "Deprecated") {
           addAnnotation(Suppress("DEPRECATION"))
        }
    }
}

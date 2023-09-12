package dev.kord.codegen.generator.utils

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.toAnnotationSpec

/**
 * Adds all the annotations from [function] to this [FunSpec].
 */
fun FunSpec.Builder.addAnnotationsFromFunction(function: KSFunctionDeclaration) {
    function.annotations.forEach {
        val packageName = it.annotationType.resolve().declaration.packageName.asString()
        // Do not add the platform annotations like @JvmStatic or @JsName, since they don't apply in most cases
        // Or annotations like @Suppress
        if (packageName != "kotlin.jvm" && packageName != "kotlin.js" && packageName != "kotlin") {
            addAnnotation(it.toAnnotationSpec())
        }
    }
}

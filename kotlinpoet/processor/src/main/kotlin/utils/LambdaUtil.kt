package dev.kord.codegen.generator.utils

import com.google.devtools.ksp.symbol.KSCallableReference
import com.google.devtools.ksp.symbol.KSTypeAlias
import com.google.devtools.ksp.symbol.KSTypeReference

/**
 * Checks whether this [KSTypeReference] resolves to a lambda declaration.
 */
fun KSTypeReference.isCallableType(): Boolean {
    if (element is KSCallableReference) return true
    val resolvedType = resolve().declaration
    return if (resolvedType is KSTypeAlias) {
        resolvedType.type.isCallableType()
    } else {
        false
    }
}
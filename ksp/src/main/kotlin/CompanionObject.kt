package dev.kord.codegen.ksp

import com.google.devtools.ksp.symbol.KSClassDeclaration

/**
 * Returns the companion object for this [KSClassDeclaration] or `null` if there is none.
 */
public val KSClassDeclaration.companionDeclaration: KSClassDeclaration?
    get() = declarations.filterIsInstance<KSClassDeclaration>().firstOrNull(KSClassDeclaration::isCompanionObject)

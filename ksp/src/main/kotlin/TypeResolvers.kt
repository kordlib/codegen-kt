package dev.kord.codegen.ksp

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.findActualType
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.*
import dev.kord.codegen.ksp.processor.getProcessorAnnotation

/**
 * Get all symbols with specified annotation.
 * Note that in multiple round processing, only symbols from deferred symbols of last round and symbols from newly generated files will be returned in this function.
 *
 * @param A the annotation
 * @param inDepth whether to check symbols in depth, i.e. check symbols from local declarations. Operation can be expensive if true.
 * @return Elements annotated with the specified annotation.
 * @see Resolver.getSymbolsWithAnnotation
 */
public inline fun <reified A : Annotation> Resolver.getSymbolsWithAnnotation(inDepth: Boolean = false): Sequence<KSAnnotated> =
    getSymbolsWithAnnotation(A::class.qualifiedName!!, inDepth)

/**
 * Returns all [KSAnnotations][KSAnnotation] from [A].
 *
 * @see getAnnotationsByType
 */
public inline fun <reified A : Annotation> KSAnnotated.getAnnotationsByType(): Sequence<KSAnnotation> {
    val annotationKClass = A::class
    return this.annotations.filter {
        it.shortName.getShortName() == annotationKClass.simpleName && it.annotationType.resolve().declaration
            .qualifiedName?.asString() == annotationKClass.qualifiedName
    }
}

/**
 * Returns an [KSAnnotation] from [A].
 *
 * @see getAnnotationsByType
 */
public inline fun <reified A : Annotation> KSAnnotated.getAnnotationByType(): KSAnnotation =
    getAnnotationsByType<A>().first()

/**
 * Checks whether an [KSAnnotation] is of type [A].
 */
public inline fun <reified A : Annotation> KSAnnotation.isOfType(): Boolean = isOfType(A::class.qualifiedName!!)

/**
 * Checks whether an [KSTypeReference] is of type [T].
 */
public inline fun <reified T> KSTypeReference.isOfType(): Boolean = isOfType(T::class.qualifiedName!!)

/**
 * Checks whether an [KSAnnotation] is of type by it's [qualifiedName].
 */
public fun KSAnnotation.isOfType(qualifiedName: String): Boolean = annotationType.isOfType(qualifiedName, canBeTypeAlias = false)

/**
 * Checks whether an [KSTypeReference] is of type by it's [qualifiedName].
 *
 * @param canBeTypeAlias whether the type could resolve to a type alias
 * (only set this to false if you know what it does)
 */
public fun KSTypeReference.isOfType(qualifiedName: String, canBeTypeAlias: Boolean = true): Boolean {
    if (!canBeTypeAlias && doesNotMatchFast(qualifiedName)) return false

    val declaration = resolve()
        .declaration

    val actualDeclaration = if (declaration is KSTypeAlias) {
        if (declaration.type.doesNotMatchFast(qualifiedName)) return false
        declaration.findActualType()
    } else {
        declaration
    }
    return actualDeclaration.qualifiedName?.asString() == qualifiedName
}

/**
 * Fast check whether a [KSTypeReference] definitively does not match, this does not mean that it does it returning true
 */
internal fun KSTypeReference.doesNotMatchFast(qualifiedName: String): Boolean {
    val referencedName = (element as? KSClassifierReference)?.referencedName()

    return referencedName != qualifiedName && referencedName != qualifiedName.substringAfterLast('.')
}

/**
 * Checks whether this is a reference to a classifier.
 */
@Suppress("RecursivePropertyAccessor")
public val KSReferenceElement.isClassifierReference: Boolean
    get() = when (this) {
        is KSDynamicReference, is KSCallableReference -> false
        is KSClassifierReference -> true
        is KSDefNonNullReference -> enclosedType.isClassifierReference
        is KSParenthesizedReference -> element.isClassifierReference
        else -> error("Unexpected KSReferenceElement: $this")
    }

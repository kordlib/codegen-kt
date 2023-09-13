package dev.kord.codegen.ksp.annotations

import kotlin.reflect.KClass

public const val NO_DELEGATION: String = "NO_DELEGATION"

/**
 * Instructs the processor to inline the constructor required for [function][functionName] in [forClass].
 *
 * ### Name Mapping
 *
 * This looks up all builder functions of the specified type (e.g., addType(TypeSpec) looks up
 * TypeSpec.`class`, TypeSpec.`object` etc. to addClass, addObject)
 *
 * If the function already exists (e.g., addAnnotation), please use [nameMapping]
 *
 * ### Ignoring constructors
 * Sometimes specs have builder not usable in every context, e.g. FunSpec.Builder.getter(), does not make sense
 * for TypeSpec.Builder.addFunction(), in that case please use [ignoreBuilders]
 *
 * ### Delegation
 * If the [nameProperty] is set to anything but [NO_DELEGATION] the processor will generate functions you can use for
 * using property delegation for naming
 *
 * ```kotlin
 * val prop by addProperty<String> {
 *     initializer("%S", "")
 * }
 * ````
 *
 * ### Example
 * ```kotlin
 * fun FileSpec.Builder.addClass(name: String, builder: TypeSpecBuilder = {}) =
 *   addType(`class`(name, builder))
 * ```
 *
 * @property nameMapping optional name mapping (See [NameMapping])
 * @property useQualifiedName whether to use a qualified name for the called constructor function
 */
@MustBeDocumented
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
@ProcessorAnnotation(packageName = "dev.kord.codegen.generator.constructor_inliner")
public annotation class InlineConstructor(
    val forClass: KClass<*>,
    val functionName: String,
    val nameMapping: Array<NameMapping> = [],
    val ignoreBuilders: Array<String> = [],
    val useQualifiedName: Boolean = false,
    @NullIfDefault
    val nameProperty: String = NO_DELEGATION
) {

    /**
     * Allows changing the name of the generated constructor function.
     *
     * e.g. `NameMapping("Annotation", "AnnotationClass") turns `addAnnotation` into `addAnnotationClass`
     */
    @MustBeDocumented
    @Target() // Only usable as argument of @InlineConstructor
    @Retention(AnnotationRetention.SOURCE)
    public annotation class NameMapping(val originalName: String, val newName: String)
}

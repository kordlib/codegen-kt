package dev.kord.codegen.ksp.annotations

/**
 * This annotation instructs the processor to generate a data class wrapper around the annotation with
 * factory functions to instantiate it from a `KSAnnotation`.
 *
 * # Type mappings
 * KSP does not represent all types identical to the ones in the actual annotation, please use the mapping below
 *
 * | Annotation type        | Wrapper type                                 |
 * |------------------------|----------------------------------------------|
 * | Primitives and Strings | same as in annotation                        |
 * | Array<T>               | List<WrapperType>                            |
 * | KClass                 | KSType                                       |
 * | AnnotationType         | KSAnnotation or another wrapper if available |
 *
 * # Factory function
 *
 * In order to obtain an instance of the wrapper type, please use the generated constructor function which takes an
 * `KSAnnotation`
 *
 * # Obtaining the annotations from `KSAnnotated`
 *
 * If the Annotation is annotated with [Repeatable] use the generated KSAnnotated.getAnnotationNames function
 * otherwise use the generated KSAnnotated.getAnnotationName function
 *
 * # Nullables
 *
 * If you want to make a property in the wrapper type nullable annotate it with [NullIfDefault] and set it's default
 * value to some magic value
 */
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS)
@ProcessorAnnotation(packageName = "dev.kord.codegen.ksp.processor")
public annotation class ProcessorAnnotation(
    val packageName: String
)

/**
 * Instructs the processor to use null, if [AnnotationArguments.isDefault] returns `true` for this property.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
public annotation class NullIfDefault

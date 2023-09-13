package dev.kord.codegen.ksp.annotations

private const val packageName = "dev.kord.codegen.ksp.processor"

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
@ProcessorAnnotation(packageName)
public annotation class ProcessorAnnotation(
    val packageName: String
)

/**
 * Instructs the processor to use null, if `AnnotationArguments.isDefault` returns `true` for this property.
 */
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
public annotation class NullIfDefault

/**
 * Instructs the processor to use value of [name], if `AnnotationArguments.isDefault` returns `true` for this property.
 *
 * @property name the name of the property to use as a default
 */
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
@ProcessorAnnotation(packageName)
public annotation class OtherIfDefault(val name: String)

/**
 * Instructs the processor to validate that either of [names] are present.
 *
 * @property exclusive whether there should be only once exclusive value
 */
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Repeatable
@ProcessorAnnotation(packageName)
public annotation class Either(val names: Array<String>, val exclusive: Boolean = false)

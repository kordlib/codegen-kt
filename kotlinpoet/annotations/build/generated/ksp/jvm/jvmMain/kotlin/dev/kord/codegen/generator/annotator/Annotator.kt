@file:Suppress(names = arrayOf("DataClassPrivateConstructor"))

package dev.kord.codegen.generator.annotator

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import dev.kord.codegen.ksp.annotations.AnnotationArguments.Companion.arguments
import dev.kord.codegen.ksp.annotations.AnnotationArguments.NonNullAnnotationArguments.Companion.notNull
import dev.kord.codegen.ksp.getAnnotationsByType
import dev.kord.codegen.ksp.annotations.Annotator as Annotation

/**
 * Data class representation of [Annotation].
 * @see Companion.Annotator
 */
public data class Annotator private constructor(
  public val fromPackage: String,
  public val ignore: List<String>,
) {
  public companion object {
    /**
     * Creates an [Annotator] from an [KSAnnotation].
     */
    public fun Annotator(`annotation`: KSAnnotation): Annotator {
      val arguments = `annotation`.arguments<Annotation>().notNull()

      val fromPackage = arguments[Annotation::fromPackage]
      val ignore = arguments[Annotation::ignore]
      return Annotator(fromPackage, ignore)
    }
  }
}

/**
 * Returns a [Sequence] of all [Annotator] annotations on this [element](KSAnnotated)
 */
public fun KSAnnotated.getAnnotators(): Sequence<Annotator> = getAnnotationsByType<Annotation>().map(Annotator.Companion::Annotator)

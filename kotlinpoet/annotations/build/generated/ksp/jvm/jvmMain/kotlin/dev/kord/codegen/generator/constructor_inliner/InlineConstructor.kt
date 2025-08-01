@file:Suppress(names = arrayOf("DataClassPrivateConstructor"))

package dev.kord.codegen.generator.constructor_inliner

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSType
import dev.kord.codegen.ksp.annotations.AnnotationArguments.Companion.arguments
import dev.kord.codegen.ksp.annotations.AnnotationArguments.NonNullAnnotationArguments.Companion.notNull
import dev.kord.codegen.ksp.getAnnotationsByType
import dev.kord.codegen.ksp.annotations.InlineConstructor as Annotation

/**
 * Data class representation of [Annotation].
 * @see Companion.InlineConstructor
 */
public data class InlineConstructor private constructor(
  public val forClass: KSType,
  public val functionName: String,
  public val nameMapping: List<NameMapping>,
  public val ignoreBuilders: List<String>,
  public val useQualifiedName: Boolean,
  public val nameProperty: String?,
) {
  /**
   * Data class representation of [Annotation.NameMapping].
   * @see Companion.NameMapping
   */
  public data class NameMapping private constructor(
    public val originalName: String,
    public val newName: String,
  ) {
    public companion object {
      /**
       * Creates an [NameMapping] from an [KSAnnotation].
       */
      public fun NameMapping(`annotation`: KSAnnotation): NameMapping {
        val arguments = `annotation`.arguments<Annotation.NameMapping>().notNull()

        val originalName = arguments[Annotation.NameMapping::originalName]
        val newName = arguments[Annotation.NameMapping::newName]
        return NameMapping(originalName, newName)
      }
    }
  }

  public companion object {
    /**
     * Creates an [InlineConstructor] from an [KSAnnotation].
     */
    public fun InlineConstructor(`annotation`: KSAnnotation): InlineConstructor {
      val arguments = `annotation`.arguments<Annotation>().notNull()

      val forClass = arguments[Annotation::forClass]
      val functionName = arguments[Annotation::functionName]
      val nameMapping = arguments[Annotation::nameMapping].map(NameMapping.Companion::NameMapping)
      val ignoreBuilders = arguments[Annotation::ignoreBuilders]
      val useQualifiedName = arguments[Annotation::useQualifiedName]
      val nameProperty = arguments[Annotation::nameProperty].takeIf {
        !arguments.isDefault(Annotation::nameProperty)}

      return InlineConstructor(forClass, functionName, nameMapping, ignoreBuilders, useQualifiedName, nameProperty)
    }
  }
}

/**
 * Returns a [Sequence] of all [InlineConstructor] annotations on this [element](KSAnnotated)
 */
public fun KSAnnotated.getInlineConstructors(): Sequence<InlineConstructor> = getAnnotationsByType<Annotation>().map(InlineConstructor.Companion::InlineConstructor)

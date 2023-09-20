package dev.kord.codegen.ksp.annotations

@Repeatable
@MustBeDocumented
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
@ProcessorAnnotation("dev.kord.codegen.generator.annotator")
public annotation class Annotator(
    val fromPackage: String,
    val ignore: Array<String> = []
)
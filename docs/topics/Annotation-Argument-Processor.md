# Annotation Argument Processor

Even though the Annotation Argument API provides an easier to use API than the
default KSP API, it can still require some boiler-plate to setup. For this reason an annotation processor is provided
to generate that boiler plate for you

## Adding the Annotation processor

```kotlin
dependencies {
    compileOnly("dev.kord.codegen", "ksp-annotations", "%version%")
    ksp("dev.kord.codegen", "ksp-processor", "%version%")
}
```

Please refer to the [KSP Documentation](https://kotlinlang.org/docs/ksp-multiplatform.html#compilation-and-processing)
for other cases

## Use the generated code

Let's say we have this Annotation

```kotlin
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
    public annotation class NameMapping(val originalName: String, val newName: String)
}
```

after running the processor you can now use the following methods to access an annotation

```kotlin
InlineConstructor(ksAnnotation)
ksAnnotated.getInlineConstructors() // Sequence<InlineConstructor>

// Please note if @InlineConstructor would not be annotated with @Repeatable this function would be generated instead
ksAnnotated.getInlineConstructor() // InlineConstructor?
```

Please note that the actual class returned is a "data class representation" of the annotation

```kotlin

public data class InlineConstructor private constructor(
    public val forClass: KSType, // KClass turns into KSType
    public val functionName: String,
    public val nameMapping: List<NameMapping>, // Array turns into List
    public val ignoreBuilders: List<String>, // Array turns into list
    public val useQualifiedName: Boolean,
    public val nameProperty: String?, // This becomes nullable, because it is annotated with @NullIfDefault
) {
    public data class NameMapping private constructor(
        public val originalName: String,
        public val newName: String,
    )
}
```

> You can find the full
> code [here](https://github.com/kordlib/codegen-kt/blob/main/ksp-annotations/src/commonMain/kotlin/InlineConstructor.kt)


Please read the documentation of the `ProcessorAnnotation`
annotation [here](https://codegen.kord.dev/api/ksp/dev.kord.codegen.ksp.annotations/-processor-annotation/index.html)

### Default values

The processor also allows you to use some other default values than in you annotation, for example `null` or default to
another propery

```kotlin
const val DEFAULT_VALUE = "DEFAULT!"

annotation class TestAnnotation(
    @NullIfDefault
    val nullable: String = DEFAULT_VALUE // will be represented as "null" if not explicitly defined
    // Will be the same as "nullable" if default
    // Please note that since "nullable" is annotated with @NullIfDefault other will be nullable too
    @OtherIfDefault("nullable")
val other: String = DEFAULT_VALUE
)
```

### Validation

You are also able to validate that certain values are different, let's say we want either an int or a string value,
but not both or none

```kotlin
const val NO_INT = -1
const val NO_STRING = ""

@Eihter(["intValue", "stringValue"], exclusive = true)
annotation class ExpressionValue(
    @NullIfDefault
    val intValue: Int = NO_INT,
    @NullIfDefault
    val stringValue: Int = NO_STRING,
)
```

This will throw an `IllegalStateException` in the [factory functions](#use-the-generated-code) when
none or both values are present

> Note that all properties used in an `@Either` annotation must be annotated with `@NullIfDefault`
>
{style="warning"}

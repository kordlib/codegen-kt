# Annotation Argument API

Codegen.kt offers a type safe way to access annotation arguments from an `KSAnnotation` instance

> Please note that all of these types are nullable by default because of
> [google/ksp#885](https://github.com/google/ksp/issues/885), if you don't target KMP use the `notNull` function

```kotlin
annotation class TestAnnotation(
    val type: KClass<*>,
    val boolean: Boolean
)

fun KSAnnotation.process() {
    val arguments = arguments<TestAnnotation>()
    
    val type: KType = arguments[TestAnnotation::type]
    val boolean: Boolean = arguments[TestAnnotation::boolean]
}
```

Full documentation can be found [here](https://codegen.kord.dev/api/ksp/dev.kord.codegen.ksp.annotations/index.html)

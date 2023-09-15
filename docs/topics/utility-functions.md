# Utility functions

In addition to the generated DSL, codegen.kt adds a bunch of handwritten utility functions.

## emptyCodeBlock()

Returns an empty code block

```kotlin
public fun emptyCodeBlock(): CodeBlock
```

## indentWithSpaces()

Indents the file with the specified amount of spaces

```kotlin
public fun FileSpec.Builder.indentWithSpaces(width: Int = 4)
```

## withNameAllocator()

Allows for usage of the name allocator like this

```kotlin
withNameAllocator {
    val name1 = newName("T")
    val name2 = newName("T")
}
```

## ClassName.parameterizedBy()

Allows parameterizing a ClassName by a generic type.

```kotlin
LIST.parameterizedBy<String>() // gives you the type name for List<String>
```

## Enum.asMemberName()

Allows to convern an Enum constant into a MemberName

```kotlin
val EXACTLY_ONCE = InvocationKind.EXACTLY_ONCE.asMemberName()
```

## KCallable.asMemberName()

> This function is experimental and unstable
>
{style="warning"}

```kotlin
val STRING_PLUS = String::plus.asMemberName()
val STRING_LENGTH = String::length.asMemberName()
```

### Documentation

Full documentation of these functions can be found [here](https://codegen.kord.dev/api/kotlinpoet)

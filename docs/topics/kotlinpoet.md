# KotlinPoet DSL

<var name="artifactName" value="kotlinpoet"/>
<var name="annotationProcessor" value="❌"/>
<include from="module.md" element-id="module_tldr"/>
<include from="module.md" element-id="installation"/>

## DSL types

The entire KotlinPoet DSL is generated from the normal KotlinPoet source code. Therefore, there are certain types of
generated DSL functions which also allow you to know what code you can migrate to the DSL and what stays the same.

### Factory functions {id="factory_functions"}

Almost everything in KotlinPoet is represented by an immutable specification class (Spec) and one or more corresponding constructor
functions in its companion object, which return a builder. These will simply be replaced by a top-level factory function
which takes the name of the spec class and takes a builder lambda:

```kotlin
FunSpec.builder("func")
    .build()
// Turns into
FunSpec("func") {
    // code
}

CodeBlock.builder()
    .add("code")
    .build()
// turns into
CodeBlock {
    
}
```

However, these builder functions are not always called builder; for example, the TypeSpec builder has different types
like `TypeSpec.classBuilder()` or `TypeSpec.interfaceBuilder()`. 
In this DSL, these become extensions of the corresponding spec class with the name
of the builder type (the part of the function name before `Builder`).

```kotlin
TypeSpec.classBuilder("name")
    .build()
// turns into
`class`("name") {
   // code 
}
```

Another option is a factory function, which directly returns the constructed class directly, which is usually called `get`.

```kotlin
AnnotationSpec.get(Suppress("UNUSED"))
// turns into
AnnotationSpec(Suppress("UNUSED"))
```

### Scoping functions
Some functions in KotlinPoet allow you to `begin` and `end` some sort of scope like a control flow in a `CodeBlock` or 
similar, these are what we refer to as scoping functions in the generator.

```kotlin
buildCodeBlock {
    beginControlFlow("if (1 == 2)")
    addCode("println(%S)", "it was true")
    endControlFlow()
}
```

In this case we generate a `withScope` function which begins the scope, calls some lambda and ends the scope:
```kotlin
CodeBlock {
    withControlFlow("if (1 == 2)") {
        addCode("println(%S)", "it was true")
    }
}
```

### Constructor inlining
The KotlinPoet API always allows you to add `Spec`s to other `Spec`s (e.g. TypeSpec.addType() is for adding
classes, interfaces, etc.). However, in most cases you only construct that spec type to immediately add it to another
spec, therefore we do something we call "constructor inlining", where we take the [factory function](#factory_functions)
and generate a version of the "addSpec" function to directly call the constructor.

```kotlin
FileSpec("dev.kord", "CoolClass") {
    addProperty("property", STRING) { // for PropertySpec.builder
        getter { // for PropertySpec.Builder.getter, which takes a FunSpec.getterBuilder()
            
        }
    }
    addClass("CoolNestedClass") // for TypeSpec.classBuilder
    addAnnotation(Suppress("UNUSED")) // for AnnotationSpec.get
    
}
```

### Reification
Kotlin has the ability to retain type parameters using 
[reification](https://kotlinlang.org/docs/inline-functions.html#reified-type-parameters), which can be useful if you 
want to specify types names as a literal (e.g. the type of a property). 
Therefore, the DSL generates a reified version of any function which takes either a `TypeName`, `ClassName` or `KClass`:

```kotlin
addFunction<String>("sayHello") {
    addCode("return·%S", "hello")
}

```

## What's next
- [Learn about how to name class/file members](member-naming.md)
- [Read the generated Dokka documentation](https://codegen.kord.dev/api/kotlinpoet)

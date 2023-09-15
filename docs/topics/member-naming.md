# Member naming

> This example uses `ParameterSpec`s, but everything can be translated 1:1 for `PropertySpec`s.
> 
{style="note"}

One of the most important things in programming is naming, and this isn't different in code generation; but there often are cases in which names become magic values in different places.
For example, here all we want to do is to return a parameter of a function:

```kotlin
FunSpec("returnString") {
    addParameter<String>("text")
    returns<String>()
    addCode("return·%L", "text")
}
```
However, even in this basic use case, we already have to put the name twice. If we change it for whatever reason
or make a mistake copying the name, we have to fix it in two places.
Another way is to use the `%N` placeholder, but, this requires us to store the `ParameterSpec` somewhere.

```kotlin
val parameterSpec = ParameterSpec<String>("text")
FunSpec("returnString") {
    addParameter(parameterSpec)
    returns<String>()
    addCode("return·%L", parameterSpec)
}
```

This is already better, because now we only write the name once. 
Just one issue: now we have this ugly `parameterSpec` variable.
Luckily, the DSL allows us to use the return value of the addParameter function instead!

```kotlin
FunSpec("returnString") {
    val text = addParameter<String>("text")
    returns<String>()
    addCode("return·%L", text)
}
```

So this is looking already a lot better. In a lot of instances, you can end up writing the name twice, but if the parameter & variable names are identical, you can use property delegation 
[similar to the Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html#using_kotlin_delegated_properties).

```kotlin
FunSpec("returnString") {
    val text by addParameter<String>()
    returns<String>()
    addCode("return·%L", text)
}
```

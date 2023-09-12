# Member naming

> This example uses `ParameterSpec`s, but everything can be translated 1:1 for `PropertySpec`s
> 
{style="note"}

One of the most important things in programming is naming, and this isn't different in Code generation, however, there
often are cases in which names become magic values in different places, for example, here all we want to do is to return
a parameter of a function

```kotlin
FunSpec("returnString") {
    addParameter<String>("text")
    returns<String>()
    addCode("return·%L", "text")
}
```

however, even in this basic use case, we already have to put the name twice, so if we change it for whatever reason
or make a mistake copying the name, we have to fix it in two places, another way is to use the `%N` placeholder, 
 however, this requires us to store the parameter spec somewhere.

```kotlin
val parameterSpec = ParameterSpec<String>("text")
FunSpec("returnString") {
    addParameter(parameterSpec)
    returns<String>()
    addCode("return·%L", parameterSpec)
}
```

This is already better, because now we only write the name once, however, now we have this ugly `parameterSpec` variable.
Luckily, the DSL allows us to use the return value of the addParameter function instead

```kotlin
FunSpec("returnString") {
    val text = addParameter<String>("text")
    returns<String>()
    addCode("return·%L", text)
}
```

So this is looking already a lot better. However, in a lot of instances, you can end up writing the name twice. If the 
parameter name and the variable name is identical, you can use property delegation 
[similar to the Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html#using_kotlin_delegated_properties)

```kotlin
FunSpec("returnString") {
    val text by addParameter<String>()
    returns<String>()
    addCode("return·%L", text)
}
```

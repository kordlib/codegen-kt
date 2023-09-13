package dev.kord.codegen.kotlinpoet.test

import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import dev.kord.codegen.kotlinpoet.*
import kotlin.test.Test

@OptIn(DelicateKotlinPoetApi::class)
private fun main() {
    val fileSpec = FileSpec("dev.schlaubi", "test") {
        addKotlinDefaultImports()
        addClass("CoolClass") {
            addAnnotation<Suppress>()
            addAnnotation(Suppress("INVISIBLE_MEMBER"))

            val test by addProperty<String> {
                initializer("%S", "test")
            }

            addProperty("delegatingTest", test.type) {
                mutable(true)

                getter {
                    addCode("return·%N", test)
                }
                setter {
                    val value by addParameter(test.type)
                    addCode("%N = %N", test, value)
                }
            }

            addProperty<String>("delegatingDelegate") {
                delegate("::%N", test)
            }

            addFunction("getTest") {
                returns<String>()
                addCode("return·%N", test)
            }
        }
    }

    println(fileSpec)
}

class ExampleTest {
    @Test
    fun test() = main()

    @Test
    fun test2() {
        fun generateListOf() = FunSpec("minOf") {
            addAnnotation(Suppress("unused"))
            val a = addParameter<Int>("a")
            val b = addParameter<Int>("b")

            returns<Boolean>()
            addCode("return·")

        }

        println(generateListOf())
    }
}



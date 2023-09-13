@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

import dev.kord.codegen.gradle.CodeGenerationExtension
import dev.kord.codegen.gradle.DownloadSourceTask
import java.lang.Boolean as JBoolean

plugins {
    org.jetbrains.kotlin.multiplatform
    com.google.devtools.ksp
}

val codeGenerationExtension = extensions.create("codeGeneration", CodeGenerationExtension::class)
val generationSourceAttribute = Attribute.of("generationSource", JBoolean::class.java)
val downloadSources by tasks.creating(DownloadSourceTask::class) {
    dependency = codeGenerationExtension.dependency
}

kotlin {
    explicitApi()
    jvm()

    targets {
        jvm {
            attributes {
                attribute(generationSourceAttribute, false as java.lang.Boolean)
            }
        }
        jvm("generationSource") {
            attributes {
                attribute(generationSourceAttribute, true as java.lang.Boolean)
            }

            compilations.all {
                compilerOptions.configure {
                    freeCompilerArgs.add("-Xjvm-default=all")
                    // TODO: Suppress warnings once KT-8087 hits
                }
            }
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("dev.kord.codegen.kotlinpoet.CodeGenInternal")
        }

        commonMain {
            kotlin.srcDir("build/generated/ksp/generationSource/generationSourceMain/kotlin")
        }

        named("generationSourceMain") {
            kotlin.srcDir(downloadSources.destinationDirectory.map { it.dir("main") })
        }
    }
}

tasks {
    named("compileKotlinGenerationSource") {
        dependsOn(downloadSources)
    }

    afterEvaluate {
        val ksp = named("kspKotlinGenerationSource") {
            dependsOn(downloadSources)
        }

        listOf("compileKotlinJvm", "jvmSourcesJar").forEach {
            named(it) {
                dependsOn(ksp)
            }
        }
    }
}

ksp {
    arg { listOf("package-name=${codeGenerationExtension.packageName.get()}") }
}

dependencies {
    "kspGenerationSource"(project(":kotlinpoet:processor"))
}

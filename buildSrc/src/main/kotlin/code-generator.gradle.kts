import com.google.devtools.ksp.gradle.KspTask
import dev.kord.codegen.gradle.CodeGenerationExtension
import dev.kord.codegen.gradle.DownloadSourceTask

plugins {
    org.jetbrains.kotlin.jvm
    com.google.devtools.ksp
}

val codeGenerationExtension = extensions.create("codeGeneration", CodeGenerationExtension::class)
val downloadSources by tasks.creating(DownloadSourceTask::class) {
    dependency = codeGenerationExtension.dependency
}

kotlin {
    explicitApi()
    target.compilations {
        create("generationSource") {
            compileTaskProvider.configure {
                compilerOptions {
                    freeCompilerArgs.add("-Xjvm-default=all")
                    // TODO: Suppress warnings once KT-8087 hits
                }
            }
        }
    }

    sourceSets {
        main {
            languageSettings.optIn("dev.kord.codegen.kotlinpoet.CodeGenInternal")
            kotlin.srcDir("build/generated/ksp/generationSource/kotlin")
        }

        named("generationSource") {
            kotlin.srcDir(downloadSources.destinationDirectory.map { it.dir("commonMain") })
        }
    }
}

tasks {
    afterEvaluate {
        val kspGenerationSourceKotlin by getting(KspTask::class) {
            dependsOn(downloadSources)
            // We don't need to reify twice, actual reification occurs in main source
            commandLineArgumentProviders.add(CommandLineArgumentProvider{ listOf("only-reify=com.squareup.kotlinpoet") })
        }
        compileKotlin {
            dependsOn(kspGenerationSourceKotlin)
        }
        named("kspKotlin") {
            dependsOn(kspGenerationSourceKotlin)
        }
    }
}


ksp {
    arg { listOf("package-name=${codeGenerationExtension.packageName.get()}") }
}

dependencies {
    "kspGenerationSource"(project(":kotlinpoet:processor"))
}

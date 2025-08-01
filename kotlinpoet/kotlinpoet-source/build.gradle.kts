import com.google.devtools.ksp.gradle.KspAATask
import dev.kord.codegen.gradle.DownloadSourceTask

plugins {
    org.jetbrains.kotlin.multiplatform
    com.google.devtools.ksp
}

val downloadSources by tasks.registering(DownloadSourceTask::class) {
    dependency = libs.kotlinpoet.asProvider().map { "${it.group}:${it.name}:${it.version}" }
}

kotlin {
    jvm {
        compilerOptions {
            freeCompilerArgs.add("-Xjvm-default=all")
        }
    }

    sourceSets {
        commonMain {
            kotlin.srcDir("build/generation-source/commonMain")

            dependencies {
                implementation(projects.kotlinpoet)
            }
        }

        jvmMain {
            kotlin.srcDir("build/generation-source/jvmMain")

            dependencies {
                implementation(kotlin("reflect"))
            }
        }
    }

    compilerOptions {
        optIn.addAll(
            "dev.kord.codegen.kotlinpoet.CodeGenInternal",
            "kotlin.contracts.ExperimentalContracts",
            "com.squareup.kotlinpoet.DelicateKotlinPoetApi"
        )
        suppressWarnings = true
    }
}

dependencies {
    kspCommonMainMetadata(projects.kotlinpoet.processor)
    "kspJvm"(projects.kotlinpoet.processor)
}

ksp {
    arg { listOf("package-name=dev.kord.codegen.kotlinpoet", "only-reify=com.squareup.kotlinpoet") }
}

tasks {
    withType<KspAATask> {
        dependsOn(downloadSources)
    }
}

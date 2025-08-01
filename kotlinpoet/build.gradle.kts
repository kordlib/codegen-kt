import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation
import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest

plugins {
    org.jetbrains.kotlin.multiplatform
    com.google.devtools.ksp
    `kord-publishing`
}

kotlin {
    explicitApi()

    jvm()

    sourceSets {
        all {
            languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
        }

        commonMain {
            dependencies {
                api(libs.kotlinpoet)
                compileOnly(projects.kotlinpoet.internalAnnotations)
            }
        }

        jvmMain {
            kotlin.srcDir("kotlinpoet-source/build/generated/ksp/jvm/jvmMain/kotlin")
            kotlin.srcDir("build/generated/ksp/jvm/jvmMain/kotlin")

            dependencies {
                implementation(kotlin("reflect"))
            }
        }

        jvmTest {
            dependencies {
                implementation(kotlin("test-junit5"))
            }
        }
    }

    compilerOptions {
        optIn.addAll("dev.kord.codegen.kotlinpoet.CodeGenInternal", "kotlin.contracts.ExperimentalContracts")
        freeCompilerArgs.add("-Xdont-warn-on-error-suppression")
    }


    @OptIn(ExperimentalAbiValidation::class)
    abiValidation {
        enabled = true
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
    withType<KotlinJvmTest> {
        useJUnitPlatform()
    }

    named("compileKotlinJvm") {
        dependsOn(":kotlinpoet:kotlinpoet-source:kspKotlinJvm")
    }
}

plugins {
    org.jetbrains.kotlin.multiplatform
    com.google.devtools.ksp
}

kotlin {
    explicitApi()

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                compileOnly(projects.ksp.annotations)
            }
        }

        named("jvmMain") {
            kotlin.srcDir("build/generated/ksp/jvm/jvmMain/kotlin")
            dependencies {
                implementation(projects.ksp)
            }
        }
    }
}

dependencies {
    "kspJvm"(projects.ksp.processor)
}

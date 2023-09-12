plugins {
    org.jetbrains.kotlin.multiplatform
    com.google.devtools.ksp
}

kotlin {
    explicitApi()
    jvm()
}

dependencies {
    commonMainImplementation(projects.ksp)
    "kspJvm"(projects.kspProcessor)
}

kotlin {
    sourceSets {
        named("jvmMain") {
            kotlin.srcDir("build/generated/ksp/jvm/jvmMain")
        }
    }
}


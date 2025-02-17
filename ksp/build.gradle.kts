plugins {
    org.jetbrains.kotlin.jvm
    org.jetbrains.kotlinx.`binary-compatibility-validator`
    `jvm-test-suite`
    `kord-publishing`
    com.google.devtools.ksp
}

kotlin {
    explicitApi()
}

dependencies {
    api(libs.ksp.api)
    ksp(libs.codegen.kotlinpoet) {
        version {
            strictly(libs.codegen.kotlinpoet.get().version!!)
        }
    }
    ksp(libs.codegen.ksp) {
        version {
            strictly(libs.codegen.ksp.asProvider().get().version!!)
        }
    }
    ksp(libs.codegen.ksp.processor) {
        version {
            strictly(libs.codegen.ksp.processor.get().version!!)
        }
    }
    testImplementation(libs.mockk)
    testImplementation(kotlin("test-junit5"))
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
    }
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter()
        }
    }
}

apiValidation {
    ignoredProjects.add("annotations")
    ignoredProjects.add("processor")
}

tasks {
    afterEvaluate {
        named("kspTestKotlin") {
            dependsOn(jar)
        }
    }
}

subprojects {
    afterEvaluate {
        mavenPublishing {
            coordinates(group.toString(), "ksp-$name", version.toString())
        }
    }
}

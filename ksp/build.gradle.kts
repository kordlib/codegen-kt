plugins {
    org.jetbrains.kotlin.jvm
    `jvm-test-suite`
    `kord-publishing`
    com.google.devtools.ksp
}

kotlin {
    explicitApi()
}

dependencies {
    api(libs.ksp.api)
    ksp(libs.codegen.ksp.processor)
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

subprojects {
    afterEvaluate {
        mavenPublishing {
            coordinates(group.toString(), "ksp-$name", version.toString())
        }
    }
}

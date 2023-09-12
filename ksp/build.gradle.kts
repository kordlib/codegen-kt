plugins {
    org.jetbrains.kotlin.jvm
    `jvm-test-suite`
    `kord-publishing`
}

kotlin {
    explicitApi()
}

dependencies {
    api(libs.ksp.api)
    testImplementation(libs.mockk)
    testImplementation(kotlin("test-junit5"))
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter()
        }
    }
}

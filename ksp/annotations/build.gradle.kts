plugins {
    org.jetbrains.kotlin.multiplatform
    `kord-publishing`
    com.google.devtools.ksp
}

base {
    archivesName = "ksp-annotations"
}

kotlin {
    explicitApi()

    jvm()
    js(IR) {
        browser()
        nodejs()
    }

    mingwX64()
    linuxX64()
    linuxArm64()

// TODO: CI infra for darwin targets
//    ios()
//    watchos()
//    tvos()
//    macosX64()
//    macosArm64()

    sourceSets {
        named("jvmMain") {
            kotlin.srcDirs("build/generated/ksp/jvm/jvmMain/kotlin")
            dependencies {
                compileOnly(libs.ksp.api)
                compileOnly(libs.codegen.ksp)
            }
        }
    }
}

dependencies {
    "kspJvm"(libs.codegen.ksp.processor)

}

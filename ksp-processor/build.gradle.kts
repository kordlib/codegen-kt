plugins {
    org.jetbrains.kotlin.jvm
    `kord-publishing`
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
    }
}

dependencies {
    implementation(libs.ksp.api)
    implementation(libs.kotlinpoet.ksp)
    implementation(libs.codegen.kotlinpoet)
    implementation(libs.codegen.ksp)
}

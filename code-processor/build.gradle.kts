plugins {
    org.jetbrains.kotlin.jvm
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
    }
}

dependencies {
    implementation(libs.kotlinpoet)
    implementation(libs.kotlinpoet.ksp)
    implementation(libs.ksp.api)
    implementation(projects.kspAnnotations)
    implementation(projects.ksp)
}

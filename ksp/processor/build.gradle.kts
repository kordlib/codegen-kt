plugins {
    org.jetbrains.kotlin.jvm
    `kord-publishing`
}

base {
    archivesName = "ksp-processor"
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    implementation(libs.kotlinpoet.ksp)
    implementation(libs.ksp.api)
    implementation(libs.codegen.kotlinpoet)
    implementation(libs.codegen.ksp)
    implementation(projects.ksp.annotations)
}

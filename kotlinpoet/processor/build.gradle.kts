plugins {
    org.jetbrains.kotlin.jvm
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xcontext-parameters", "-Xdont-warn-on-error-suppression")
    }
}

dependencies {
    implementation(libs.codegen.kotlinpoet)
    implementation(libs.kotlinpoet.ksp)
    implementation(libs.ksp.api)
    implementation(projects.kotlinpoet.internalAnnotations)
    implementation(projects.ksp.annotations)
    implementation(projects.ksp)
}

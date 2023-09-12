plugins {
    org.jetbrains.kotlin.jvm
    `kord-publishing`
}

dependencies {
    implementation(libs.ksp.api)
    implementation(libs.kotlinpoet.ksp)
}

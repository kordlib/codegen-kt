plugins {
    org.jetbrains.kotlin.multiplatform
    `kord-publishing`
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
}

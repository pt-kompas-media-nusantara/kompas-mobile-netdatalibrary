import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelight)
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
    id("co.touchlab.skie") version "0.9.3" // https://skie.touchlab.co/intro
    kotlin("plugin.serialization") version "2.0.21" // https://kotlinlang.org/docs/serialization.html#add-plugins-and-dependencies
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sql.android.driver)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sql.native.driver)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.kompasid.netdata"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

multiplatformSwiftPackage {
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("16") }
    }
    outputDirectory(
        File(
            projectDir,
            "swiftpackage"
        )
    )  // Optional: specify where to output the Swift package
    zipFileName("NetDataLibrary")  // Custom name for the generated ZIP file
}
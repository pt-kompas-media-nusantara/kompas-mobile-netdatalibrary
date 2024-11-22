import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
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
        }
        commonTest.dependencies {
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
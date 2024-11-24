import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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

    val xcframeworkName = "Shared"
    val xcf = XCFramework(xcframeworkName)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            // generate xcframework : https://kotlinlang.org/docs/native-spm.html#set-up-remote-integration
            // ./gradlew :shared:assembleSharedXCFramework, output :
            // - /Users/kompasdigital/Documents/project/kmp/NetDataLibrary/shared/build/XCFrameworks/release/Shared.xcframework
            // -  /Users/kompasdigital/Documents/project/kmp/NetDataLibrary/shared/build/XCFrameworks/debug/Shared.xcframework
            // swift package compute-checksum Shared.xcframework.zip

            baseName = xcframeworkName

            binaryOption("bundleId", "org.example.${xcframeworkName}")
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.kompasid.netdatalibrary"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

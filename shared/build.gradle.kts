import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.9.3"
    kotlin("plugin.serialization") version "1.9.20"
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
            implementation(libs.kotlinx.coroutines.core) // https://github.com/Kotlin/kotlinx.coroutines
            implementation(libs.kotlinx.datetime) // https://github.com/Kotlin/kotlinx-datetime
            // cek lagi untuk kotlinx ada yang masih bisa di pakai tidak : https://github.com/orgs/Kotlin/repositories?q=kotlinx

            // lagi lagi : ada lagi nggak untuk ktor
            // io.ktor:ktor-client-resources, bagus untuk di gunakan cek lagi https://ktor.io/docs/client-resources.html#install_plugin. tapi sepertinya bentrok dengan ktor-serialization-kotlinx-json
            implementation(libs.bundles.ktor)

            // done
            implementation(libs.koin.core)
            implementation(libs.napier) // logging
            implementation(libs.konform) // validation
            implementation(libs.multiplatform.settings.no.arg) // Storage for UserDefaults & SharedPreferences
//            implementation(libs.jwtparser) // JWTDecode
        }
        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.ktor.client.okhttp) // http client for android
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin) // http client for ios

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            // implementation(libs.koin.test)
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

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.20"
    id("co.touchlab.skie") version "0.10.0"
    id("maven-publish")
    // id("com.google.devtools.ksp") version "2.0.20-1.0.25" // https://insert-koin.io/docs/setup/annotations : belum di gunakan coba cek lagi cara penggunaannya bagaiaman
}

//group = "com.example"
//version = "1.0"
//
//publishing {
//    repositories {
//        maven {
//            //...
//        }
//    }
//}

kotlin {
//    withSourcesJar(publish = true)
    androidTarget {
        publishLibraryVariants("release")
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
            // ./gradlew :shared:assembleSharedXCFramework
            // output :
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

//            api(libs.koin.annotations) // sementara di komen
//            implementation(libs.koin.ksp.compiler)  // sementara di komen // gue nggak tau pakai pakai implementation atau api karna dokumentasinya tidak jelas api(libs.koin.ksp.compiler)

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
            implementation(libs.kotlinx.coroutines.test)
        }
    }

//    // KSP Common sourceSet
//    sourceSets.named("commonMain").configure {
//        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
//    }
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["kotlin"]) // This includes all targets
            groupId = "com.kompasid.netdatalibrary"
            artifactId = "shared"
            version = "0.0.3"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url =
                uri("https://maven.pkg.github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary") // Replace with your info
            credentials {
                // sample
                username =
                    System.getenv("GITHUB_ACTOR") // Or your preferred method to provide credentials.
                password =
                    System.getenv("GITHUB_TOKEN") // Create a token in github and give it read/write permission
            }
        }
    }
}

android {
    namespace = "com.kompasid.netdatalibrary"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

//// KSP Tasks
//dependencies {
//    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
//    add("kspAndroid", libs.koin.ksp.compiler)
//    add("kspIosX64", libs.koin.ksp.compiler)
//    add("kspIosArm64", libs.koin.ksp.compiler)
//    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
//}
//
//// Trigger Common Metadata Generation from Native tasks
//project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
//    if (name != "kspCommonMainKotlinMetadata") {
//        dependsOn("kspCommonMainKotlinMetadata")
//    }
//}
//generate arr bisa pakai ini
//https://eamedina87.medium.com/how-to-add-an-android-aar-library-in-kotlin-multiplatform-6286041d135d
///Users/kompasdigital/Documents/project/kmp/NetDataLibrary/shared/build/outputs/aar/shared-release.aar
// ./gradlew :<module-name>:assembleRelease
// ./gradlew :shared:assembleRelease
// ./gradlew :<module-name>:assembleRelease --info
// ./gradlew :<module-name>:assembleRelease --debug
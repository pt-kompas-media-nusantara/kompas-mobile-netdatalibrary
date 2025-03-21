plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
    // id("com.google.devtools.ksp") version "2.0.20-1.0.25"
}

android {
    namespace = "com.kompasid.netdatalibrary.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.kompasid.netdatalibrary.android"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // default
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)

    // additional
    implementation(libs.coil.compose)
    implementation(libs.androidx.navigation.compose)
    // implementation(libs.koin.bom)
    implementation(libs.koin.android) // deprecated, koin-android - ViewModel DSL in favor of new centralized DSL in koin-core
//    // Java Compatibility
//    implementation(libs.koin.android.compat)
//    // Jetpack WorkManager
//    implementation(libs.koin.androidx.workmanager)
//    // Navigation Graph
//    implementation(libs.koin.androidx.navigation)
//    // App Startup
//    implementation(libs.koin.androidx.startup)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)
    // Koin for Ktor
    implementation(libs.koin.ktor)




    // implementation(libs.koin.androidx.compose)

    // Koin
    // kalau ada error coba barter aja penggunaan api, implementationn dan ksp ini soalnya tidak standart, referensi https://insert-koin.io/docs/setup/annotations
    // api(libs.koin.annotations)
//    implementation(libs.koin.annotations) // gue nggak
//    ksp(libs.koin.ksp.compiler)
    // implementation(libs.koin.ksp.compiler)


    // Koin Test features
    testImplementation(libs.koin.test)
    // Koin for JUnit 4
    // testImplementation(libs.koin.test.junit4)
    // Koin for JUnit 5
    testImplementation(libs.koin.test.junit5)
    debugImplementation(libs.compose.ui.tooling)
}
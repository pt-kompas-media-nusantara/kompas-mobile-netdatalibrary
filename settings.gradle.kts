enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        //maven { url 'https://jitpack.io' }
    }
}

rootProject.name = "NetDataLibrary"
include(":androidApp")
include(":shared")
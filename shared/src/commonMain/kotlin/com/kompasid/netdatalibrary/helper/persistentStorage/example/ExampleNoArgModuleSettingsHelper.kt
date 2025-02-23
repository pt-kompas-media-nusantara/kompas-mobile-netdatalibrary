package com.kompasid.netdatalibrary.helper.persistentStorage.example

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

enum class ExampleSettingsKey(val value: String) {
    USERNAME("USERNAME"),
    DARK_MODE("DARK_MODE"),
    NOTIFICATION_ENABLED("NOTIFICATION_ENABLED")
}

class ExampleNoArgModuleSettingsHelper(private val settings: Settings) {

    // Save Data with Generic Type
    fun <T> save(key: ExampleSettingsKey, value: T) {
        when (value) {
            is String -> settings[key.value] = value
            is Int -> settings[key.value] = value
            is Boolean -> settings[key.value] = value
            is Float -> settings[key.value] = value
            is Long -> settings[key.value] = value
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    // Load Data with Generic Type
    fun <T> load(key: ExampleSettingsKey, defaultValue: T): T {
        return when (defaultValue) {
            is String -> settings[key.value, defaultValue] as T
            is Int -> settings[key.value, defaultValue] as T
            is Boolean -> settings[key.value, defaultValue] as T
            is Float -> settings[key.value, defaultValue] as T
            is Long -> settings[key.value, defaultValue] as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }
}

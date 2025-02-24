package com.kompasid.netdatalibrary.helper.persistentStorage

import com.kompasid.netdatalibrary.base.logger.Logger
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.serialization.removeValue
import kotlinx.serialization.KSerializer

@Suppress("UNCHECKED_CAST")
class SettingsHelper(
    private val settings: Settings,
    private val flowSettings: FlowSettings,
) {

    suspend fun <T> save(key: KeySettingsType, value: T, serializer: KSerializer<T>? = null) {
        when (value) {

            is String -> {
                val load = load(key, "")
                if (load != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $load, newValue: $value" }
                    flowSettings.putString(key.key, value)
                }
            }

            is Int -> {
                val load = load(key, 100)
                if (load != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $load, newValue: $value" }
                    flowSettings.putInt(key.key, value)
                }
            }

            is Boolean -> {
                val load = load(key, false)
                if (load != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $load, newValue: $value" }
                    flowSettings.putBoolean(key.key, value)
                }
            }

            is Float -> {
                val load = load(key, 0.0)
                if (load != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $load, newValue: $value" }
                    flowSettings.putFloat(key.key, value)
                }
            }

            else -> {
                if (serializer != null && serializer != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $serializer, newValue: $value" }
                    settings.encodeValue(serializer, key.key, value)
                } else {
                    throw IllegalArgumentException("Unsupported type for key: ${key.key}, value: $value")
                }
            }

        }
    }


    // Function to load generic value
    suspend fun <T> load(
        key: KeySettingsType,
        defaultValue: T? = null,
        serializer: KSerializer<T>? = null
    ): T {
        return when (defaultValue) {
            is String -> {
                flowSettings.getStringOrNullFlow(key.key)
            }

            is Int -> {
                flowSettings.getIntOrNullFlow(key.key)
            }

            is Boolean -> {
                flowSettings.getBooleanOrNullFlow(key.key)
            }

            is Float -> {
                flowSettings.getFloatOrNullFlow(key.key)
            }

            else -> {
                if (serializer != null) {
                    settings.decodeValueOrNull(serializer, key.key)
                } else {
                    Logger.error { "Unsupported data type for key: ${key.key}" }
                    throw IllegalArgumentException("Unsupported data type for key: ${key.key}")
                }

            }
        } as T
    }

    suspend fun <T> remove(
        key: KeySettingsType,
        isSerialized: Boolean = false,
        serializer: KSerializer<T>? = null
    ) {
        if (isSerialized) {
            if (serializer != null) {
                settings.removeValue(serializer, key.key)
            } else {
                throw IllegalArgumentException("Serializer cannot be null when removing serialized value for key: ${key.key}")
            }
        } else {
            flowSettings.remove(key.key)
        }
    }


    // Function to remove all values
    suspend fun removeAll() {
        settings.clear()
        flowSettings.clear()
    }


}

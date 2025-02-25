package com.kompasid.netdatalibrary.helper.persistentStorage

import com.kompasid.netdatalibrary.base.logger.Logger
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.serialization.removeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.KSerializer

@Suppress("UNCHECKED_CAST")
class SettingsHelper(
    private val settings: Settings,
    private val flowSettings: FlowSettings,
) {
    //selanjutnya
//    set and get list : String, Int, Boolean, Float, serializer
//    satuan serializer
//    setelah itu menampilkan login result state
//    setelah itu menampilkan history user memberhip
//    setelah itu menampilkan serializer list active dan expired
//    save serializer UserDetailResInterceptor
//    load serializer UserDetailResInterceptor
    suspend fun <T> save(key: KeySettingsType, value: T, serializer: KSerializer<T>? = null) {
        when (value) {

            is String -> {
                val current = get(key, "")
                if (current != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putString(key.key, value)
                }
            }

            is Int -> {
                val current = get(key, 100)
                if (current != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putInt(key.key, value)
                }
            }

            is Boolean -> {
                val current = get(key, false)
                if (current != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putBoolean(key.key, value)
                }
            }

            is Float -> {
                val current = get(key, 0.0)
                if (current != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putFloat(key.key, value)
                }
            }

            else -> {
                if (serializer != null && serializer != value) {
                    val current = get(key, serializer)
                    if (current != value) {
                        Logger.debug { "Saving key: ${key.key}, oldValue: $serializer, newValue: $value" }
                        settings.encodeValue(serializer, key.key, value)
                    }
                } else {
                    throw IllegalArgumentException("Unsupported type for key: ${key.key}, value: $value")
                }
            }

        }
    }


    // Function to load generic value
    fun <T> load(
        key: KeySettingsType,
        defaultValue: T,
        serializer: KSerializer<T>? = null
    ): Flow<T> {
        val result: Flow<T> = when (defaultValue) {
            is String -> flowSettings.getStringFlow(key.key, defaultValue).map { it as T }
            is Int -> flowSettings.getIntFlow(key.key, defaultValue).map { it as T }
            is Boolean -> flowSettings.getBooleanFlow(key.key, defaultValue).map { it as T }
            is Float -> flowSettings.getFloatFlow(key.key, defaultValue).map { it as T }
            else -> {
                if (serializer != null) {
                    flow {
                        val decodedValue =
                            settings.decodeValueOrNull(serializer, key.key) ?: defaultValue
                        emit(decodedValue)
                    }
                } else {
                    throw IllegalArgumentException("Unsupported data type for key: ${key.key}")
                }
            }

        }

        // ✅ Logging nilai sebenarnya tanpa coroutine tambahan
        result.onEach { value ->
            Logger.debug { "Load key: ${key.key}, Value: $value" }
        }

        return result
    }

    suspend fun <T> get(
        key: KeySettingsType,
        defaultValue: T,
        serializer: KSerializer<T>? = null
    ): T {
        return load(key, defaultValue, serializer).first() // Ambil nilai pertama dari Flow
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


//// ✅ Menyimpan List<String>
//viewModelScope.launch {
//    settingsHelper.save(KeySettingsType.LIST_STRING, listOf("Apple", "Banana", "Cherry"), ListSerializer(String.serializer()))
//}
//
//// ✅ Mengambil List<String>
//viewModelScope.launch {
//    val savedList = settingsHelper.get(KeySettingsType.LIST_STRING, emptyList(), ListSerializer(String.serializer()))
//    Logger.debug { "Loaded List: $savedList" }
//}

package com.kompasid.netdatalibrary.helper.persistentStorage

import com.kompasid.netdatalibrary.base.logger.Logger
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.serialization.removeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

@Suppress("UNCHECKED_CAST")
class SettingsHelper(
    private val settings: Settings,
    private val flowSettings: FlowSettings,
) {

    suspend fun <T> save(key: KeySettingsType, value: T, serializer: KSerializer<T>? = null) {
        when (value) {

            is String -> {
                val current = get(key, "", isFromSave = true)
                if (current != value) {
                    Logger.debug { "💽 Saving String key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putString(key.key, value)
                }
            }

            is Int -> {
                val current = get(key, 100, isFromSave = true)
                if (current != value) {
                    Logger.debug { "💽 Saving Int key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putInt(key.key, value)
                }
            }

            is Boolean -> {
                val current = get(key, false, isFromSave = true)
                if (current != value) {
                    Logger.debug { "💽 Saving Boolean key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putBoolean(key.key, value)
                }
            }

            is Float -> {
                val current = get(key, 0.0, isFromSave = true)
                if (current != value) {
                    Logger.debug { "💽 Saving Float key: ${key.key}, oldValue: $current, newValue: $value" }
                    flowSettings.putFloat(key.key, value)
                }
            }

            is List<*> -> {
                when {
                    value.isNotEmpty() && value.all { it is String } -> {
                        val listString = value as List<String>
                        val json =
                            Json.encodeToString(ListSerializer(String.serializer()), listString)
                        val current = get(key, "[]", isFromSave = true)

                        if (current != json) {
                            Logger.debug { "💽 Saving List<String> key: ${key.key}, oldValue: $current, newValue: $json" }
                            flowSettings.putString(key.key, json)
                        }
                    }

                    value.isNotEmpty() && value.all { it is Boolean } -> {
                        val listBoolean = value as List<Boolean>
                        val json =
                            Json.encodeToString(ListSerializer(Boolean.serializer()), listBoolean)
                        val current = get(key, "[]", isFromSave = true)

                        if (current != json) {
                            Logger.debug { "💽 Saving List<Boolean> key: ${key.key}, oldValue: $current, newValue: $json" }
                            flowSettings.putString(key.key, json)
                        }
                    }

                    value.isNotEmpty() && value.all { it is Int } -> {
                        val listInt = value as List<Int>
                        val json = Json.encodeToString(ListSerializer(Int.serializer()), listInt)
                        val current = get(key, "[]", isFromSave = true)

                        if (current != json) {
                            Logger.debug { "💽 Saving List<Int> key: ${key.key}, oldValue: $current, newValue: $json" }
                            flowSettings.putString(key.key, json)
                        }
                    }

                    value.isNotEmpty() && value.all { it is Float } -> {
                        val listFloat = value as List<Float>
                        val json =
                            Json.encodeToString(ListSerializer(Float.serializer()), listFloat)
                        val current = get(key, "[]", isFromSave = true)

                        if (current != json) {
                            Logger.debug { "💽 Saving List<Float> key: ${key.key}, oldValue: $current, newValue: $json" }
                            flowSettings.putString(key.key, json)
                        }
                    }

                    else -> {
                        throw IllegalArgumentException("Unsupported List type for key: ${key.key}, value: $value")
                    }
                }
            }

            else -> {
                if (serializer != null) {
//                    tidak bisa menggunakan validasi di bawah, karna kotlin semuanya object reference
//                    Logger.info { "serializer: ${value.toString()}" }
//                    val current = get(key, value, serializer, isFromSave = true)
//                    Logger.info { "current: ${current.toString()}" }
//                    if (current.toString() != value.toString()) {
//                        Logger.debug { "💽 Saving key: ${key.key}, oldValue: $current, newValue: $value" }
//                        settings.encodeValue(serializer, key.key, value)
//                    }
                    Logger.debug { "💽 Saving Serializer key: ${key.key}, newValue: $value" }
                    settings.encodeValue(serializer, key.key, value)
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

            is List<*> -> {
                when {
                    defaultValue.all { it is String } ->
                        flowSettings.getStringFlow(key.key, "[]").map { json ->
                            try {
                                Json.decodeFromString(
                                    ListSerializer(String.serializer()),
                                    json
                                ) as T
                            } catch (e: Exception) {
                                Logger.error { "Error decoding List<String> for ${key.key}: ${e.message}" }
                                defaultValue
                            }
                        }

                    defaultValue.all { it is Boolean } ->
                        flowSettings.getStringFlow(key.key, "[]").map { json ->
                            try {
                                Json.decodeFromString(
                                    ListSerializer(Boolean.serializer()),
                                    json
                                ) as T
                            } catch (e: Exception) {
                                Logger.error { "Error decoding List<Boolean> for ${key.key}: ${e.message}" }
                                defaultValue
                            }
                        }

                    defaultValue.all { it is Int } ->
                        flowSettings.getStringFlow(key.key, "[]").map { json ->
                            try {
                                Json.decodeFromString(ListSerializer(Int.serializer()), json) as T
                            } catch (e: Exception) {
                                Logger.error { "Error decoding List<Int> for ${key.key}: ${e.message}" }
                                defaultValue
                            }
                        }

                    defaultValue.all { it is Float } ->
                        flowSettings.getStringFlow(key.key, "[]").map { json ->
                            try {
                                Json.decodeFromString(ListSerializer(Float.serializer()), json) as T
                            } catch (e: Exception) {
                                Logger.error { "Error decoding List<Float> for ${key.key}: ${e.message}" }
                                defaultValue
                            }
                        }

                    else -> throw IllegalArgumentException("Unsupported List type for key: ${key.key}")
                }
            }

            else -> {
                if (serializer != null) {
                    flowSettings.getStringFlow(key.key, "")
                        .map { json ->
                            try {
                                if (json.isNotEmpty()) {
                                    Json.decodeFromString(serializer, json)
                                } else {
                                    defaultValue
                                }
                            } catch (e: Exception) {
                                Logger.error { "❌ Error decoding object for ${key.key}: ${e.message}" }
                                defaultValue
                            }
                        }
                } else {
                    throw IllegalArgumentException("Unsupported data type for key: ${key.key}")
                }
            }
        }

        result.onEach { value ->
            Logger.debug { "📚 Load ${defaultValue.toString()} key: ${key.key}, Value: $value" }
        }

        return result
    }

    suspend fun <T> get(
        key: KeySettingsType,
        defaultValue: T,
        serializer: KSerializer<T>? = null,
        isFromSave: Boolean = false
    ): T {
        if (isFromSave) {
            Logger.debug { "💽💽 Get ${defaultValue.toString()} Key: ${key.key}, Default: $defaultValue" }
        } else {
            Logger.debug { "🔍 Get ${defaultValue.toString()} Key: ${key.key}, Default: $defaultValue" }
        }
        return when {
            defaultValue is List<*> -> {
                val jsonString = settings.getStringOrNull(key.key) ?: "[]"
                Logger.debug { "📥 [GET] Raw JSON from Storage for ${key.key}: $jsonString" }

                try {
                    val result: T = when {
                        defaultValue.all { it is String } ->
                            Json.decodeFromString(
                                ListSerializer(String.serializer()),
                                jsonString
                            ) as T

                        defaultValue.all { it is Boolean } ->
                            Json.decodeFromString(
                                ListSerializer(Boolean.serializer()),
                                jsonString
                            ) as T

                        defaultValue.all { it is Int } ->
                            Json.decodeFromString(ListSerializer(Int.serializer()), jsonString) as T

                        defaultValue.all { it is Float } ->
                            Json.decodeFromString(
                                ListSerializer(Float.serializer()),
                                jsonString
                            ) as T

                        else -> throw IllegalArgumentException("Unsupported List type for key: ${key.key}")
                    }

                    Logger.debug { "✅ [GET] Successfully decoded list for ${key.key}: $result" }
                    result
                } catch (e: Exception) {
                    Logger.error { "❌ [GET] Error decoding list for key ${key.key}: ${e.message}" }
                    defaultValue
                }
            }

            serializer != null -> {
                try {
                    settings.decodeValue(serializer, key.key, defaultValue)
//                    val jsonString = settings.getStringOrNull(key.key)
//                    if (jsonString != null) {
//                        Json.decodeFromString(serializer, jsonString)
//                    } else {
//                        defaultValue
//                    }
                } catch (e: Exception) {
                    Logger.error { "❌ [GET] Error decoding object for key ${key.key}: ${e.message}" }
                    defaultValue
                }
            }


            else -> load(key, defaultValue, serializer).first()
        }
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

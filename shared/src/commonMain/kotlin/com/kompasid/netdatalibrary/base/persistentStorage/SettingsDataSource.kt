package com.kompasid.netdatalibrary.base.persistentStorage

import com.kompasid.netdatalibrary.base.logger.Logger
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@Suppress("UNCHECKED_CAST")
class SettingsDataSource {

    private val settings: Settings = Settings()

    // StateFlow untuk data reaktif
    private val _stringFlowMap: MutableMap<String, MutableStateFlow<String?>> = mutableMapOf()
    private val _intFlowMap: MutableMap<String, MutableStateFlow<Int?>> = mutableMapOf()
    private val _booleanFlowMap: MutableMap<String, MutableStateFlow<Boolean?>> = mutableMapOf()
    private val _stringListFlowMap: MutableMap<String, MutableStateFlow<List<String>?>> =
        mutableMapOf()

    // Function to save generic value with StateFlow update
    fun <T> save(key: KeySettingsType, value: T) {
        when (value) {
            is String -> {
                settings.putString(key.key, value)
                getStringFlow(key).update {
                    Logger.debug { "Updated ${key.key} to: $value" }
                    value
                }
            }

            is Int -> {
                settings.putInt(key.key, value)
                getIntFlow(key).update {
                    Logger.debug { "Updated ${key.key} to: $value" }
                    value
                }
            }

            is Boolean -> {
                settings.putBoolean(key.key, value)
                getBooleanFlow(key).update {
                    Logger.debug { "Updated ${key.key} to: $value" }
                    value
                }
            }

            is List<*> -> {
                // Memeriksa jika List tersebut berisi String
                if (value.all { it is String }) {
                    @Suppress("UNCHECKED_CAST")
                    val stringList = value as List<String>
                    // Mengonversi List<String> menjadi string yang dipisahkan koma
                    val stringValue = stringList.joinToString(",")
                    settings.putString(key.key, stringValue)
                    getStringListFlow(key).update {
                        Logger.debug { "Updated ${key.key} to: $stringList" }
                        stringList
                    }
                } else {
                    throw IllegalArgumentException("Unsupported list type, must be a List<String>")
                }
            }

            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    // Function to load generic value
    fun <T> load(key: KeySettingsType, defaultValue: T): T {
        return when (defaultValue) {
            is String -> settings.getString(key.key, defaultValue)
            is Int -> settings.getInt(key.key, defaultValue)
            is Boolean -> settings.getBoolean(key.key, defaultValue)
            is List<*> -> {
                // Cek apakah defaultValue adalah List<String>
                val listDefaultValue = defaultValue as? List<String>
                if (listDefaultValue != null) {
                    // Menangani List<String> yang disimpan sebagai string dipisahkan koma
                    val storedValue = settings.getString(key.key, "")
                    if (storedValue.isNotEmpty()) {
                        // Mengonversi string yang dipisahkan koma kembali menjadi List<String>
                        storedValue.split(",")
                    } else {
                        listDefaultValue // Mengembalikan defaultValue jika tidak ada data yang disimpan
                    }
                } else {
                    throw IllegalArgumentException("Unsupported list type, must be List<String>")
                }
            }

            else -> throw IllegalArgumentException("Unsupported type")
        } as T
    }

    // Function to remove a value
    fun remove(key: KeySettingsType) {
        settings.remove(key.key)
        // Mengupdate StateFlow dengan null
        _stringFlowMap[key.key]?.update {
            Logger.debug { "Removed ${key.key}" }
            null
        }
        _intFlowMap[key.key]?.update {
            Logger.debug { "Removed ${key.key}" }
            null
        }
        _booleanFlowMap[key.key]?.update {
            Logger.debug { "Removed ${key.key}" }
            null
        }
    }

    // Function to remove all values
    fun removeAll() {
        settings.clear()
        _stringFlowMap.values.forEach {
            it.update {
                Logger.debug { "Cleared String setting" }
                null
            }
        }
        _intFlowMap.values.forEach {
            it.update {
                Logger.debug { "Cleared Int setting" }
                null
            }
        }
        _booleanFlowMap.values.forEach {
            it.update {
                Logger.debug { "Cleared Boolean setting" }
                null
            }
        }
    }

    // Mengambil StateFlow untuk String
    fun getStringFlow(key: KeySettingsType): MutableStateFlow<String?> {
        return _stringFlowMap.getOrPut(key.key) {
            val value = settings.getStringOrNull(key.key)
            Logger.debug { "Initialized ${key.key} with: $value" }
            MutableStateFlow(value)
        }
    }

    // Mengambil StateFlow untuk Int
    fun getIntFlow(key: KeySettingsType): MutableStateFlow<Int?> {
        return _intFlowMap.getOrPut(key.key) {
            val value = settings.getIntOrNull(key.key)
            Logger.debug { "Initialized ${key.key} with: $value" }
            MutableStateFlow(value)
        }
    }

    // Mengambil StateFlow untuk Boolean
    fun getBooleanFlow(key: KeySettingsType): MutableStateFlow<Boolean?> {
        return _booleanFlowMap.getOrPut(key.key) {
            val value = settings.getBooleanOrNull(key.key)
            Logger.debug { "Initialized ${key.key} with: $value" }
            MutableStateFlow(value)
        }
    }

    fun getStringListFlow(key: KeySettingsType): MutableStateFlow<List<String>?> {
        return _stringListFlowMap.getOrPut(key.key) {
            // Ambil string yang dipisahkan koma dari settings
            val value = settings.getString(key.key, "")
            // Mengonversi string yang dipisahkan koma menjadi List<String>
            val stringList = if (value.isNotEmpty()) {
                value.split(",") // Memisahkan string berdasarkan koma
            } else {
                emptyList() // Mengembalikan list kosong jika tidak ada value
            }
            MutableStateFlow(stringList)
        }
    }
}
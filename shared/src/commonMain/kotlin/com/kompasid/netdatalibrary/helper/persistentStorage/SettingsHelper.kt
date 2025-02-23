package com.kompasid.netdatalibrary.helper.persistentStorage

import com.kompasid.netdatalibrary.base.logger.Logger
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class SettingsHelper(private val settings: Settings) {

    // StateFlow untuk data reaktif
    private val _stringFlowMap: MutableMap<String, MutableStateFlow<String?>> = mutableMapOf()
    private val _intFlowMap: MutableMap<String, MutableStateFlow<Int?>> = mutableMapOf()
    private val _booleanFlowMap: MutableMap<String, MutableStateFlow<Boolean?>> = mutableMapOf()
    private val _stringListFlowMap: MutableMap<String, MutableStateFlow<List<String>?>> =
        mutableMapOf()


    suspend fun <T> save(key: KeySettingsType, value: T) {
        when (value) {
            is String -> {
                val flow = getStringFlow(key)
                if (flow.value != value) { // Cek apakah nilai berubah
                    settings.putString(key.key, value)
                    flow.update { value }
                }
            }

            is Int -> {
                val flow = getIntFlow(key)
                if (flow.value != value) {
                    settings.putInt(key.key, value)
                    flow.update { value }
                }
            }

            is Boolean -> {
                val flow = getBooleanFlow(key)
                if (flow.value != value) {
                    settings.putBoolean(key.key, value)
                    flow.update { value }
                }
            }

            is List<*> -> {
                if (value.all { it is String }) {
                    @Suppress("UNCHECKED_CAST")
                    val stringList = value as List<String>
                    val stringValue = stringList.joinToString(",")
                    val flow = getStringListFlow(key)
                    if (flow.value != stringList) {
                        settings.putString(key.key, stringValue)
                        flow.update { stringList }
                    }
                } else {
                    throw IllegalArgumentException("Unsupported list type, must be a List<String>")
                }
            }
        }
    }


    // Function to load generic value
    fun <T> load(key: KeySettingsType, defaultValue: T): T {
        return when (defaultValue) {
            is String -> {
                val flow = getStringFlow(key)
                val value = flow.value ?: settings.getString(key.key, defaultValue)
                Logger.debug { "Loaded ${key.key} with value: $value" }
                value
            }

            is Int -> {
                val flow = getIntFlow(key)
                val value = flow.value ?: settings.getInt(key.key, defaultValue)
                Logger.debug { "Loaded ${key.key} with value: $value" }
                value
            }

            is Boolean -> {
                val flow = getBooleanFlow(key)
                val value = flow.value ?: settings.getBoolean(key.key, defaultValue)
                Logger.debug { "Loaded ${key.key} with value: $value" }
                value
            }

            is List<*> -> {
                // Cek apakah defaultValue adalah List<String>
                val listDefaultValue = defaultValue as? List<String>
                if (listDefaultValue != null) {
                    // Menangani List<String> yang disimpan sebagai string dipisahkan koma
                    val flow = getStringFlow(key)
                    val storedValue = flow.value ?: settings.getString(key.key, "")
                    if (storedValue.isNotEmpty()) {
                        // Mengonversi string yang dipisahkan koma kembali menjadi List<String>
                        Logger.debug {
                            "${key.key} : ${storedValue.split(",")}"
                        }
                        storedValue.split(",")
                    } else {
                        Logger.debug {
                            "${key.key} : $listDefaultValue"
                        }
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
        _stringFlowMap.remove(key.key)
        _intFlowMap.remove(key.key)
        _booleanFlowMap.remove(key.key)
        _stringListFlowMap.remove(key.key)
    }


    // Function to remove all values
    fun removeAll() {
        settings.clear()
        _stringFlowMap.clear()
        _intFlowMap.clear()
        _booleanFlowMap.clear()
        _stringListFlowMap.clear()
    }

    fun loadAll() {
        CoroutineScope(Dispatchers.IO).launch {
            val keySettings = listOf(
                KeySettingsType.ACCESS_TOKEN,
                KeySettingsType.REFRESH_TOKEN,
                KeySettingsType.IS_VERIFIED,
                KeySettingsType.DEVICE_KEY_ID,
                KeySettingsType.IS_SOCIAL,
                KeySettingsType.USER_ID,
                KeySettingsType.FIRST_NAME,
                KeySettingsType.LAST_NAME,
                KeySettingsType.EMAIL,
                KeySettingsType.USER_GUID,
                KeySettingsType.PHONE_NUMBER,
                KeySettingsType.COUNTRY_CODE,
                KeySettingsType.COUNTRY,
                KeySettingsType.PROVINCE,
                KeySettingsType.CITY,
                KeySettingsType.EXPIRED_MEMBERSHIP,
                KeySettingsType.ACTIVE_MEMBERSHIP,
                KeySettingsType.START_DATE_MEMBERSHIP,
                KeySettingsType.END_DATE_MEMBERSHIP,
                KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP,
                KeySettingsType.GRACE_PERIOD_MEMBERSHIP,
                KeySettingsType.RUBRIK_PILIHANKU
            )

            keySettings.forEach { key ->
                load(key, "")
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
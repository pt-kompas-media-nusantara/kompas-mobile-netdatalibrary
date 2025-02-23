package com.kompasid.netdatalibrary.helper.persistentStorage

import com.kompasid.netdatalibrary.base.logger.Logger
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("UNCHECKED_CAST")
class SettingsHelper(private val settings: Settings) {

    // StateFlow untuk data reaktif
    private val _stringFlowMap: MutableMap<String, MutableStateFlow<String?>> = mutableMapOf()
    private val _intFlowMap: MutableMap<String, MutableStateFlow<Int?>> = mutableMapOf()
    private val _booleanFlowMap: MutableMap<String, MutableStateFlow<Boolean?>> = mutableMapOf()
    private val _stringListFlowMap: MutableMap<String, MutableStateFlow<List<String>?>> =
        mutableMapOf()
    private val _serializableFlowMap: MutableMap<String, MutableStateFlow<Any?>> = mutableMapOf()

    suspend fun <T> save(key: KeySettingsType, value: T, serializer: KSerializer<T>? = null) {
        when (value) {
            is String -> {
                val flow = getStringFlow(key)
                if (flow.value != value) { // Cek apakah nilai berubah
                    Logger.debug { "Saving key: ${key.key}, oldValue: ${flow.value}, newValue: $value" }
                    settings.putString(key.key, value)
                    flow.update { value }
                    Logger.debug { "Value for key: ${key.key} updated successfully to: $value" }
                }
            }

            is Int -> {
                val flow = getIntFlow(key)
                if (flow.value != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: ${flow.value}, newValue: $value" }
                    settings.putInt(key.key, value)
                    flow.update { value }
                    Logger.debug { "Value for key: ${key.key} updated successfully to: $value" }
                }
            }

            is Boolean -> {
                val flow = getBooleanFlow(key)
                if (flow.value != value) {
                    Logger.debug { "Saving key: ${key.key}, oldValue: ${flow.value}, newValue: $value" }
                    settings.putBoolean(key.key, value)
                    flow.update { value }
                    Logger.debug { "Value for key: ${key.key} updated successfully to: $value" }
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
                }
            }

            else -> {
                if (serializer != null) {
                    Logger.debug { "Saving Serializable key: ${key.key}" }
                    settings.encodeValue(serializer, key.key, value)

                    val flow = getSerializableFlow<T>(key)
                    flow.value = value

                    Logger.debug { "Serializable value for key: ${key.key} saved successfully" }
                } else {
                    throw IllegalArgumentException("Unsupported data type for key: ${key.key}, please provide a serializer.")
                }
            }

        }
    }


    // Function to load generic value
    fun <T> load(key: KeySettingsType, defaultValue: T, serializer: KSerializer<T>? = null): T {
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
                    throw IllegalArgumentException("Unsupported List type for key: ${key.key}, please provide a List.")
                }
            }

            else -> {
                if (serializer != null) {
                    Logger.debug { "Fetching Serializable key: ${key.key}" }
                    val value = settings.decodeValueOrNull(serializer, key.key) ?: defaultValue

                    val flow = getSerializableFlow<T>(key)
                    flow.value = value

                    Logger.debug { "Loaded Serializable ${key.key} with value: $value" }
                    value
                } else {
                    throw IllegalArgumentException("Unsupported data type for key: ${key.key}, please provide a serializer.")
                }
            }
        } as T
    }

    // Function to remove a value
    fun remove(key: KeySettingsType) {
        settings.remove(key.key)

        // Perbarui nilai StateFlow sebelum menghapus dari map agar UI langsung mendapatkan update
        _stringFlowMap[key.key]?.value = null
        _intFlowMap[key.key]?.value = 0
        _booleanFlowMap[key.key]?.value = false
        _stringListFlowMap[key.key]?.value = emptyList()
        _serializableFlowMap[key.key]?.value = null
    }


    // Function to remove all values
    fun removeAll() {
        settings.clear()

        // Reset semua StateFlow dengan nilai default
        _stringFlowMap.forEach { (_, flow) -> flow.value = null }
        _intFlowMap.forEach { (_, flow) -> flow.value = 0 }
        _booleanFlowMap.forEach { (_, flow) -> flow.value = false }
        _stringListFlowMap.forEach { (_, flow) -> flow.value = emptyList() }
        _serializableFlowMap.forEach { (_, flow) -> flow.value = null }

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


    fun <T> getSerializableFlow(key: KeySettingsType): MutableStateFlow<T?> =
        _serializableFlowMap.getOrPut(key.key) {
            Logger.debug { "Initialized Serializable ${key.key}" }
            MutableStateFlow(null)
        } as MutableStateFlow<T?>

}
/*
@Serializable
data class UserProfile(val name: String, val age: Int)

// Menyimpan objek UserProfile
viewModel.save(KeySettingsType.USER_PROFILE, UserProfile("Irfan", 29), UserProfile.serializer())

// Mengambil objek UserProfile
val userProfile: UserProfile = viewModel.load(KeySettingsType.USER_PROFILE, UserProfile("Guest", 0), UserProfile.serializer())
*/
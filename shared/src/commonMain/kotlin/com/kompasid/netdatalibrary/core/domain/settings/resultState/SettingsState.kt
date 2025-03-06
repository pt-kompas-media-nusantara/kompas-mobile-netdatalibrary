package com.kompasid.netdatalibrary.core.domain.settings.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SettingsState(
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    // Generic function untuk membuat StateFlow dari settingsHelper
    private fun <T> createSettingsMap(keys: List<KeySettingsType>, defaultValue: T): Map<KeySettingsType, StateFlow<T>> {
        return keys.associateWith { key ->
            settingsHelper.load(key, defaultValue).stateIn(scope, SharingStarted.Eagerly, defaultValue)
        }
    }

    // Menggunakan generic function untuk String dan Boolean
    private val settingsMap: Map<KeySettingsType, StateFlow<String>> by lazy {
        createSettingsMap(
            keys = listOf(
                KeySettingsType.ACCESS_TOKEN,
                KeySettingsType.REFRESH_TOKEN,
                KeySettingsType.DEVICE_KEY_ID
            ),
            defaultValue = ""
        )
    }

    private val settingsMapBoolean: Map<KeySettingsType, StateFlow<Boolean>> by lazy {
        createSettingsMap(
            keys = listOf(
                KeySettingsType.IS_VERIFIED,
                KeySettingsType.IS_SOCIAL
            ),
            defaultValue = false
        )
    }

    // Fungsi Generic untuk mendapatkan StateFlow berdasarkan tipe
    private fun <T> getSettingStringFlow(map: Map<KeySettingsType, StateFlow<T>>, key: KeySettingsType, default: T): StateFlow<T> {
        return map[key] ?: MutableStateFlow(default)
    }

    // Fungsi untuk mendapatkan `StateFlow<String>`
    private fun getSettingStringFlow(key: KeySettingsType): StateFlow<String> = getSettingStringFlow(settingsMap, key, "")

    // Fungsi untuk mendapatkan `StateFlow<Boolean>`
    private fun getSettingBooleanFlow(key: KeySettingsType): StateFlow<Boolean> = getSettingStringFlow(settingsMapBoolean, key, false)

    // Fungsi untuk streaming `Flow<String>`
    fun streamStringSetting(key: KeySettingsType): Flow<String> = getSettingStringFlow(key)

    // Fungsi untuk streaming `Flow<Boolean>`
    fun streamBooleanSetting(key: KeySettingsType): Flow<Boolean> = getSettingBooleanFlow(key)
}



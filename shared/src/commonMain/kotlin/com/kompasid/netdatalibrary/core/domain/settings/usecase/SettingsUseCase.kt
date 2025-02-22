package com.kompasid.netdatalibrary.core.domain.settings.usecase

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class SettingsUseCase(
    private val settingsHelper: SettingsHelper
) {

    suspend fun <T> save(key: KeySettingsType, value: T) {
        settingsHelper.save(key, value)
    }

    private fun <T> getValue(type: KeySettingsType, default: T): T {
        return settingsHelper.load(type, default)
    }

    fun remove(key: KeySettingsType) {
        settingsHelper.remove(key)
    }

    fun removeAll() {
        settingsHelper.removeAll()
    }

    fun getString(type: KeySettingsType): String = getValue(type, "")
    fun getBoolean(type: KeySettingsType): Boolean = getValue(type, false)
    fun getInt(type: KeySettingsType): Int = getValue(type, 0)


    fun loadAllSettings() {
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
                settingsHelper.load(key, "")
            }
        }
    }


}



package com.kompasid.netdatalibrary.netData.domain.SettingsDomain

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.netData.data.loginGuestData.LoginGuestRepository

class SettingsUseCase(
    private val settingsDataSource: SettingsDataSource
) {

    suspend fun loadAccessToken() {
        settingsDataSource.load(KeySettingsType.ACCESS_TOKEN, "")
    }

    suspend fun loadAllSettings() {
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
            KeySettingsType.MEMBERSHIP_EXPIRED,
            KeySettingsType.MEMBERSHIP_ACTIVE,
            KeySettingsType.MEMBERSHIP_START_DATE,
            KeySettingsType.MEMBERSHIP_END_DATE,
            KeySettingsType.MEMBERSHIP_TOTAL_GRACE_PERIOD,
            KeySettingsType.MEMBERSHIP_GRACE_PERIOD,
            KeySettingsType.RUBRIK_PILIHANKU
        )

        keySettings.forEach { key ->
            settingsDataSource.load(key, "")
        }
    }
}


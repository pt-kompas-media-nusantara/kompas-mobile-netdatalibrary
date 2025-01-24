package com.kompasid.netdatalibrary.core.data.refreshToken.model.local

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource

class RefreshTokenDataSource(
    private val settingsDataSource: SettingsDataSource
) {
    fun save(
        accessToken: String,
        refreshToken: String,
        isVerified: Boolean,
        deviceKeyId: String,
        isSocial: Boolean
    ) {
        settingsDataSource.save(KeySettingsType.ACCESS_TOKEN, accessToken)
        settingsDataSource.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
        settingsDataSource.save(KeySettingsType.IS_VERIFIED, isVerified)
        settingsDataSource.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId)
        settingsDataSource.save(KeySettingsType.IS_SOCIAL, isSocial)

    }
}
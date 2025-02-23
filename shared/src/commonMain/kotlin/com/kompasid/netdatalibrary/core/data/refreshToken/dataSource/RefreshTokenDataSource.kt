package com.kompasid.netdatalibrary.core.data.refreshToken.dataSource

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class RefreshTokenDataSource(
    private val settingsHelper: SettingsHelper
) {
    suspend fun save(
        accessToken: String,
        refreshToken: String,
        isVerified: Boolean,
        deviceKeyId: String,
        isSocial: Boolean
    ) {
//        settingsHelper.save(KeySettingsType.ACCESS_TOKEN, accessToken)
//        settingsHelper.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
//        settingsHelper.save(KeySettingsType.IS_VERIFIED, isVerified)
//        settingsHelper.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId)
//        settingsHelper.save(KeySettingsType.IS_SOCIAL, isSocial)

    }
}
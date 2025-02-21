package com.kompasid.netdatalibrary.core.data.loginEmail.models.local

import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class LoginEmailDataSource(
    private val settingsUseCase: SettingsUseCase
) {
    suspend fun save(
        accessToken: String,
        refreshToken: String,
        isVerified: Boolean,
        deviceKeyId: String,
        isSocial: Boolean
    ) {
        settingsUseCase.save(KeySettingsType.ACCESS_TOKEN, accessToken)
        settingsUseCase.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
        settingsUseCase.save(KeySettingsType.IS_VERIFIED, isVerified)
        settingsUseCase.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId)
        settingsUseCase.save(KeySettingsType.IS_SOCIAL, isSocial)

    }
}


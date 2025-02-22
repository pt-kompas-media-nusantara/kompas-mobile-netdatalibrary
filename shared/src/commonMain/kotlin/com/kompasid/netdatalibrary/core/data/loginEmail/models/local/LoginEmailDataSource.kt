package com.kompasid.netdatalibrary.core.data.loginEmail.models.local

import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class LoginEmailDataSource(
    private val settingsUseCase: SettingsUseCase
) {

    suspend fun save(
        accessToken: String,
        refreshToken: String,
        isVerified: Boolean,
        deviceKeyId: String,
        isSocial: Boolean
    ) = coroutineScope {
        listOf(
            async {
                if (settingsUseCase.getString(KeySettingsType.ACCESS_TOKEN) != accessToken) {
                    settingsUseCase.save(KeySettingsType.ACCESS_TOKEN, accessToken)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.REFRESH_TOKEN) != refreshToken) {
                    settingsUseCase.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
                }
            },
            async {
                if (settingsUseCase.getBoolean(KeySettingsType.IS_VERIFIED) != isVerified) {
                    settingsUseCase.save(KeySettingsType.IS_VERIFIED, isVerified)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.DEVICE_KEY_ID) != deviceKeyId) {
                    settingsUseCase.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId)
                }
            },
            async {
                if (settingsUseCase.getBoolean(KeySettingsType.IS_SOCIAL) != isSocial) {
                    settingsUseCase.save(KeySettingsType.IS_SOCIAL, isSocial)
                }
            },
        ).awaitAll()
    }

}

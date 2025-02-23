package com.kompasid.netdatalibrary.core.data.loginEmail.models.local

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


class LoginEmailDataSource(
    private val settingsHelper: SettingsHelper
) : ILoginEmailDataSource {

    override suspend fun save(
        accessToken: String,
        refreshToken: String,
        isVerified: Boolean,
        deviceKeyId: String,
        isSocial: Boolean
    ): Unit = coroutineScope {
        listOf(
            async {
                if ((settingsHelper.getStringFlow(KeySettingsType.ACCESS_TOKEN).value) != accessToken
                ) {
                    settingsHelper.save(KeySettingsType.ACCESS_TOKEN, accessToken)
                }

            },
            async {
                if ((settingsHelper.getStringFlow(KeySettingsType.REFRESH_TOKEN).value) != refreshToken
                ) {
                    settingsHelper.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
                }
            },
            async {
                if ((settingsHelper.getBooleanFlow(KeySettingsType.IS_VERIFIED).value) != isVerified
                ) {
                    settingsHelper.save(KeySettingsType.IS_VERIFIED, isVerified)
                }
            },
            async {
                if ((settingsHelper.getStringFlow(KeySettingsType.DEVICE_KEY_ID).value) != deviceKeyId
                ) {
                    settingsHelper.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId)
                }
            },
            async {
                if ((settingsHelper.getBooleanFlow(KeySettingsType.IS_SOCIAL).value) != isSocial
                ) {
                    settingsHelper.save(KeySettingsType.IS_SOCIAL, isSocial)
                }

            },
        ).awaitAll()
    }

}

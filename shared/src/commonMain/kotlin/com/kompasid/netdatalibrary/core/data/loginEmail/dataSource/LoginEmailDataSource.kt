package com.kompasid.netdatalibrary.core.data.loginEmail.dataSource

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
                settingsHelper.save(KeySettingsType.ACCESS_TOKEN, accessToken)
            },
            async {
                settingsHelper.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
            },
            async {
                settingsHelper.save(KeySettingsType.IS_VERIFIED, isVerified)
            },
            async {
                settingsHelper.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId)
            },
            async {
                settingsHelper.save(KeySettingsType.IS_SOCIAL, isSocial)
            },
        ).awaitAll()
    }

}

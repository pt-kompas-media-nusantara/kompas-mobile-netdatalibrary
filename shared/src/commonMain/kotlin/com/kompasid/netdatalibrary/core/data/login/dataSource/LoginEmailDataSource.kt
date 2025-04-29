package com.kompasid.netdatalibrary.core.data.login.dataSource

import com.kompasid.netdatalibrary.core.data.login.dto.response.Sso
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


class LoginEmailDataSource(
    private val settingsHelper: SettingsHelper
) : ILoginEmailDataSource {

    suspend fun save(
        accessToken: String,
        refreshToken: String,
        deviceKeyId: String,
        isPassEmpty: Boolean,
        isSocial: Boolean,
    ) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.ACCESS_TOKEN, accessToken),
                settingsHelper.saveAsync(this, KeySettingsType.REFRESH_TOKEN, refreshToken),
                settingsHelper.saveAsync(this, KeySettingsType.DEVICE_KEY_ID, deviceKeyId),
                settingsHelper.saveAsync(this, KeySettingsType.IS_PASS_EMPTY, isPassEmpty),
                settingsHelper.saveAsync(this, KeySettingsType.IS_SOCIAL, isSocial)
            ).awaitAll()
        }
    }

    suspend fun save(sso: Sso) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.SSO, sso),
            ).awaitAll()
        }
    }


}


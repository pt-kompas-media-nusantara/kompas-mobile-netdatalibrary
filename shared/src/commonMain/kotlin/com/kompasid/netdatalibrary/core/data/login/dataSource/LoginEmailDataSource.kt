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
    ): Unit = coroutineScope {
        val tasks = listOf(
            async { runCatching { settingsHelper.save(KeySettingsType.ACCESS_TOKEN, accessToken) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.REFRESH_TOKEN, refreshToken) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.IS_PASS_EMPTY, isPassEmpty) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.IS_SOCIAL, isSocial) }.getOrThrow() }
        )

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }

    suspend fun save(
        sso: Sso
    ): Unit = coroutineScope {
        val tasks = listOf(
            async { runCatching { settingsHelper.save(KeySettingsType.SSO, sso) }.getOrThrow() },
        )

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }


}


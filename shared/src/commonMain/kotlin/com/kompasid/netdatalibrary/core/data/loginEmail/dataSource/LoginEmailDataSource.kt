package com.kompasid.netdatalibrary.core.data.loginEmail.dataSource

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext


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
        val tasks = listOf(
            async { runCatching { settingsHelper.save(KeySettingsType.ACCESS_TOKEN, accessToken) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.REFRESH_TOKEN, refreshToken) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.IS_VERIFIED, isVerified) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.DEVICE_KEY_ID, deviceKeyId) }.getOrThrow() },
            async { runCatching { settingsHelper.save(KeySettingsType.IS_SOCIAL, isSocial) }.getOrThrow() }
        )

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }


}

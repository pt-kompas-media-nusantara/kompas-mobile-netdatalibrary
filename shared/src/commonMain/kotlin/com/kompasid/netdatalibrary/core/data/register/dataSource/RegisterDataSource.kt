package com.kompasid.netdatalibrary.core.data.register.dataSource

import com.kompasid.netdatalibrary.core.data.register.dto.interceptor.RegisterResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


class RegisterDataSource(
    private val settingsHelper: SettingsHelper
) : IRegisterDataSource {

    suspend fun save(data: RegisterResInterceptor): Unit = coroutineScope {
        val tasks = listOf(
            async { settingsHelper.save(KeySettingsType.ACCESS_TOKEN, data.accessToken) },
            async { settingsHelper.save(KeySettingsType.REFRESH_TOKEN, data.refreshToken) },
            async { settingsHelper.save(KeySettingsType.REGISTERED_ON, data.registeredOn) },
        )

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }
}

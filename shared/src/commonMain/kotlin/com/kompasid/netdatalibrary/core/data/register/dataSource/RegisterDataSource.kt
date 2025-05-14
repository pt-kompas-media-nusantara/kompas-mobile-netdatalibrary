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

    suspend fun save(data: RegisterResInterceptor) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.ACCESS_TOKEN, data.accessToken),
                settingsHelper.saveAsync(this, KeySettingsType.REFRESH_TOKEN, data.refreshToken),
                settingsHelper.saveAsync(this, KeySettingsType.REGISTERED_ON, data.registeredOn)
            ).awaitAll()
        }
    }
}

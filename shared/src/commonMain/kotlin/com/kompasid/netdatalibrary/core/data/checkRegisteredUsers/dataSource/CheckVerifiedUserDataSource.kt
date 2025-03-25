package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dataSource

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class CheckVerifiedUserDataSource(
    private val settingsHelper: SettingsHelper
) {
    suspend fun save(data: CheckRegisteredUsersResInterceptor): Unit = supervisorScope {
        listOf(
            KeySettingsType.IS_REGISTERED to data.registered,
            KeySettingsType.REGISTERED_BY to data.registeredBy,
            KeySettingsType.REGISTERED_ON to data.registeredOn
        ).forEach { (key, value) ->
            launch {
                runCatching {
                    settingsHelper.save(key, value)
                }.onFailure {
                    Logger.debug { "Failed to save $key: ${it.message}" }
                }
            }
        }
    }


}
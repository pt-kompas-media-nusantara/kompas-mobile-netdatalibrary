package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dataSource

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class CheckRegisteredUsersDataSource(
    private val settingsHelper: SettingsHelper
) {
    suspend fun save(data: CheckRegisteredUsersResInterceptor) {
//        coroutineScope {
//            listOf(
//                settingsHelper.saveAsync(this, KeySettingsType.REGISTERED_ON, data.registeredOn),
//            ).awaitAll()
//        }
    }


}
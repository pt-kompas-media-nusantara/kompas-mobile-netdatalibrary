package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dataSource

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class CheckVerifiedUserDataSource(
    private val settingsHelper: SettingsHelper
) {
    suspend fun save(data: CheckRegisteredUsersResInterceptor) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.REGISTERED_ON, data.registeredOn), // ini disimpan untuk di tampilkan akun link mana yang terhubung
            ).awaitAll()
        }
    }


}
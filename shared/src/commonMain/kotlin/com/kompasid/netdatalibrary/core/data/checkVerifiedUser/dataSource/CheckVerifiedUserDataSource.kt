package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dataSource

import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class CheckVerifiedUserDataSource(
    private val settingsHelper: SettingsHelper
) {
    suspend fun save(data: CheckVerifiedUserInterceptor): Unit = coroutineScope {
        listOf(
            async {
                settingsHelper.save(KeySettingsType.IS_REGISTERED, data.registered)
            },
            async {
                settingsHelper.save(KeySettingsType.REGISTERED_BY, data.registeredBy)
            },
        ).awaitAll()
    }
}
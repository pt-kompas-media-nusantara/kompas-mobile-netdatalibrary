package com.kompasid.netdatalibrary.core.data.forceUpdate.dataSource

import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.interceptor.ForceUpdateResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ForceUpdateDataSource(
    private val settingsHelper: SettingsHelper
) {

    suspend fun save(data: ForceUpdateResInterceptor): Unit = coroutineScope {
        val isDebug: Boolean = settingsHelper.get(KeySettingsType.IS_DEBUG, false)

        val tasks = mutableListOf<Deferred<Unit>>()

        tasks += settingsHelper.saveAsync(this, KeySettingsType.FORCE_UPDATE_MIN_VERSION, data.minVersion)
        tasks += settingsHelper.saveAsync(this, KeySettingsType.FORCE_UPDATE_MAX_VERSION, data.maxVersion)

        if (isDebug) {
            tasks += settingsHelper.saveAsync(this, KeySettingsType.OS_VERSION, data.mobileVersion)
        }

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }
}

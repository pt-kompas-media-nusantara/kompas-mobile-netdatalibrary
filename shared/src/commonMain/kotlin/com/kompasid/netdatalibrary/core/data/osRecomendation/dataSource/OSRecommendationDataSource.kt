package com.kompasid.netdatalibrary.core.data.osRecomendation.dataSource

import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class OSRecommendationDataSource(
    private val settingsHelper: SettingsHelper
) : IOSRecomendationDataSource {

    suspend fun save(data: OSRecommendationResInterceptor): Unit = coroutineScope {
        val isDebug: Boolean = settingsHelper.get(KeySettingsType.IS_DEBUG, false)

        val tasks = mutableListOf<Deferred<Unit>>()

        tasks += settingsHelper.saveAsync(this, KeySettingsType.OS_VERSION_RECOMENDATION, data.osRecommendation)

        if (isDebug) {
            tasks += settingsHelper.saveAsync(this, KeySettingsType.OS_VERSION, data.osVersion)
        }

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }
}

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

        tasks += async { settingsHelper.save(KeySettingsType.RECOMENDATION_MINIMUM_OS_VERSION, data.minimumOS) }
        tasks += async { settingsHelper.save(KeySettingsType.RECOMENDATION_OS_VERSION, data.osRecommendation) }

        if (isDebug) {
            tasks += async { settingsHelper.save(KeySettingsType.OS_VERSION, data.osVersion) }
        }

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }
}

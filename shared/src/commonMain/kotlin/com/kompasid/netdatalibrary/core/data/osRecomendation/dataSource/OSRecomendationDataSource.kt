package com.kompasid.netdatalibrary.core.data.osRecomendation.dataSource

import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class OSRecomendationDataSource(
    private val settingsHelper: SettingsHelper
) : IOSRecomendationDataSource {

    suspend fun save(data: OSRecommendationResInterceptor): Unit = coroutineScope {
        val tasks = listOf(
            async { settingsHelper.save(KeySettingsType.MINIMUM_OS_VERSION, data.minimumOS) },
            async { settingsHelper.save(KeySettingsType.RECOMENDATION_OS_VERSION, data.osRecommendation) },
        )

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }
}

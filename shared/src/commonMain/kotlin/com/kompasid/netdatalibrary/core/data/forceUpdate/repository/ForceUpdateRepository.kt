package com.kompasid.netdatalibrary.core.data.forceUpdate.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.interceptor.ForceUpdateResInterceptor
import com.kompasid.netdatalibrary.core.data.forceUpdate.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.forceUpdate.network.ForceUpdateApiService
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.enums.ForceUpdateType
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.enums.OSRecommendationType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.helpers.logged
import com.kompasid.netdatalibrary.helpers.times.CalculateTimeFormatter
import com.kompasid.netdatalibrary.helpers.times.RelativeTimeFormatter
import kotlinx.datetime.LocalDateTime

class ForceUpdateRepository(
    private val forceUpdateApiService: ForceUpdateApiService,
) : IForceUpdateRepository {

    suspend fun forceUpdate(): Results<ForceUpdateResInterceptor, NetworkError> {
        return try {
            when (val result = forceUpdateApiService.forceUpdate()) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()


                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

}


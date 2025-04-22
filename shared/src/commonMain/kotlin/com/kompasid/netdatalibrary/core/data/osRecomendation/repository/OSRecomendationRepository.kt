package com.kompasid.netdatalibrary.core.data.osRecomendation.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.network.OSRecomendationApiService


class OSRecomendationRepository(
    private val osRecomendationApiService: OSRecomendationApiService,
) : IOSRecomendationRepository {

    suspend fun osRecommendation(): Results<OSRecommendationResInterceptor, NetworkError> {
        return try {
            when (val result = osRecomendationApiService.osRecommendation()) {
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
package com.kompasid.netdatalibrary.core.domain.osRecomendation.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationType
import com.kompasid.netdatalibrary.core.data.osRecomendation.repository.OSRecomendationRepository
import com.kompasid.netdatalibrary.helpers.logged

class OSRecomendationUseCase(
    private val osRecomendationRepository: OSRecomendationRepository
) : IOSRecomendationUseCase {

    suspend fun osRecommendation(): Results<OSRecommendationType, NetworkError> {
        return try {
            osRecomendationRepository.osRecommendation().logged(prefix = "osRecommendation")
            Results.Success(OSRecommendationType.NO_UPDATE_OS)
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}
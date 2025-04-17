package com.kompasid.netdatalibrary.core.data.osRecomendation.mappers

import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response.OSRecomendationResponse

fun OSRecomendationResponse.toInterceptor(): OSRecommendationResInterceptor {
    return OSRecommendationResInterceptor(
        osRecommendation = osRecommendation ?: "",
        minimumOS = minimumOS ?: "",
        osVersion = osVersion ?: "",
    )

}
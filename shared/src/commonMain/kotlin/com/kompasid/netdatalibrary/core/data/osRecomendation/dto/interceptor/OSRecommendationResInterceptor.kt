package com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor


data class OSRecommendationResInterceptor(
    var minimumOS: String,
    var osRecommendation: String
)


enum class OSRecommendationType {
    NO_UPDATE_OS,
    MINOR_UPDATE_OS,
    MAJOR_UPDATE_OS,
}
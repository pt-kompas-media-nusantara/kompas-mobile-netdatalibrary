package com.kompasid.netdatalibrary.core.domain.osRecomendation.model

data class OSRecommendationInterceptor(
    val type: OSRecommendationType,
    val minRecoOS: MinRecoOSInterceptor,
)
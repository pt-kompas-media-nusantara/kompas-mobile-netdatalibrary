package com.kompasid.netdatalibrary.core.domain.osRecomendation.model

data class OSRecommendationInterceptor(
    val title: String,
    val descriptiion: String,
    val minOS: String,
    val recoOS: String,
    val type: OSRecommendationType,
)
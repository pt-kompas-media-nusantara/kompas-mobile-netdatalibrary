package com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor


data class OSRecommendationResInterceptor(
    var ios: IosRecoInterceptor
)

data class IosRecoInterceptor(
    var minimumOS: String,
    var osRecommendation: String,
    var osVersion: String,
    var userInterface: UserInterfaceRecoInterceptor
)

data class UserInterfaceRecoInterceptor(
    var osInformation: OsInformationRecoInterceptor,
    var osRecomendation: OsRecomendationRecoInterceptor
)

data class OsInformationRecoInterceptor(
    var descriptiion: String,
    var title: String
)

data class OsRecomendationRecoInterceptor(
    var descriptiion: String,
    var title: String
)

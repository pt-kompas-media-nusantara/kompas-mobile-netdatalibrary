package com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class OSRecommendationResponse(
    @SerialName("ios")
    var ios: IosRecoResponse? = null
)

@Serializable
data class IosRecoResponse(
    @SerialName("minimumOS")
    var minimumOS: String? = null,
    @SerialName("osRecommendation")
    var osRecommendation: String? = null,
    @SerialName("osVersion")
    var osVersion: String? = null,
    @SerialName("userInterface")
    var userInterface: UserInterfaceRecoResponse? = null
)

@Serializable
data class UserInterfaceRecoResponse(
    @SerialName("osInformation")
    var osInformation: OsInformationRecoResponse? = null,
    @SerialName("osRecomendation")
    var osRecomendation: OsRecomendationRecoResponse? = null
)

@Serializable
data class OsInformationRecoResponse(
    @SerialName("descriptiion")
    var descriptiion: String? = null,
    @SerialName("title")
    var title: String? = null
)

@Serializable
data class OsRecomendationRecoResponse(
    @SerialName("descriptiion")
    var descriptiion: String? = null,
    @SerialName("title")
    var title: String? = null
)

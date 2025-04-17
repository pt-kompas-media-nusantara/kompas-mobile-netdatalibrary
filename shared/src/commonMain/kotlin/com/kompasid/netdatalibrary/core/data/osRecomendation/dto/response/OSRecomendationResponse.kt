package com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class OSRecomendationResponse(
    @SerialName("minimumOS")
    var minimumOS: String? = null,
    @SerialName("osRecommendation")
    var osRecommendation: String? = null,
    @SerialName("osVersion")
    var osVersion: String? = null,
)

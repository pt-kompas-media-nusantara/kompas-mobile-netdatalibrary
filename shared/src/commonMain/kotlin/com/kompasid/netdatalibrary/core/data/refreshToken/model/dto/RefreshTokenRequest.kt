package com.kompasid.netdatalibrary.core.data.refreshToken.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    @SerialName("refreshToken") val refreshToken: String
)
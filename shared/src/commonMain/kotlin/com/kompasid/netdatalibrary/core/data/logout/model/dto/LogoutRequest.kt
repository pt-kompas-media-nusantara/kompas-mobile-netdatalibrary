package com.kompasid.netdatalibrary.core.data.logout.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequest(
    @SerialName("refreshToken")
    val refreshToken: String
)
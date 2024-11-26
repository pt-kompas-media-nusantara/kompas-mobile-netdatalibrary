package com.kompasid.netdatalibrary.netData.data.logoutData.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequest(
    @SerialName("refreshToken")
    val refreshToken: String
)
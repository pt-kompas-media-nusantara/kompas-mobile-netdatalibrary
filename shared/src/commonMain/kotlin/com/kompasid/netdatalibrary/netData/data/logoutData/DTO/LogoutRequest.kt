package com.kompasid.app.netdatamodule.Example.Data.LogoutData.DTO.ApiService

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequest(
    @SerialName("refreshToken")
    val refreshToken: String
)
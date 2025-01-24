package com.kompasid.netdatalibrary.core.data.loginEmail.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginEmailRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("device") val device: String,
    @SerialName("deviceType") val deviceType: String,
)
package com.kompasid.netdatalibrary.netData.data.loginGuestData.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginGuestRequest(
    @SerialName("email") val email: String
)
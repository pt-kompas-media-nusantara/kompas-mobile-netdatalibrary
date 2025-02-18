package com.kompasid.netdatalibrary.core.data.loginGuest.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginGuestRequest(
    @SerialName("email") val email: String
)
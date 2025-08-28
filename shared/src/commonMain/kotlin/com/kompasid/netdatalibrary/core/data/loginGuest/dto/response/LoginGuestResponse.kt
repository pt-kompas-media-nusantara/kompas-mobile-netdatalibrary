package com.kompasid.netdatalibrary.core.data.loginGuest.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginGuestResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: LoginGuestResponseData? = null,
    @SerialName("message")
    var message: String? = null,
)

@Serializable
data class LoginGuestResponseData(
    @SerialName("accessToken")
    var accessToken: String? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null
)

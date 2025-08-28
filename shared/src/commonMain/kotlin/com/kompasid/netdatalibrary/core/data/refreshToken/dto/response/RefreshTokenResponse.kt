package com.kompasid.netdatalibrary.core.data.refreshToken.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: RefreshTokenResponseData? = null,
    @SerialName("message")
    var message: String? = null,
)

@Serializable
data class RefreshTokenResponseData(
    @SerialName("accessToken")
    var accessToken: String? = null,
    @SerialName("deviceKeyId")
    var deviceKeyId: String? = null,
    @SerialName("isVerified")
    var isVerified: Boolean? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null
)

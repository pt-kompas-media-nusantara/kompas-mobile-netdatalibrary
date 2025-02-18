package com.kompasid.netdatalibrary.core.data.refreshToken.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RefreshTokenResponse(
    @SerialName("accessToken")
    val accessToken: String? = null,
    @SerialName("deviceKeyId")
    val deviceKeyId: String? = null,
    @SerialName("isPassEmpty")
    val isPassEmpty: Boolean? = null,
    @SerialName("isSocial")
    val isSocial: Boolean? = null,
    @SerialName("isVerified")
    val isVerified: Boolean? = null,
    @SerialName("refreshToken")
    val refreshToken: String? = null
)


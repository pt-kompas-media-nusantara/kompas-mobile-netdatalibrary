package com.kompasid.netdatalibrary.core.data.loginEmail.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginEmailResponse(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val data: LoginEmailResponseData? = null,
    @SerialName("message")
    val message: String? = null,
)

@Serializable
data class LoginEmailResponseData(
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
    val refreshToken: String? = null,
)


package com.kompasid.netdatalibrary.core.data.login.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: LoginResponseData? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("success")
    var success: Boolean? = null
)

@Serializable
data class LoginResponseData(
    @SerialName("accessToken")
    var accessToken: String? = null,
    @SerialName("deviceKeyId")
    var deviceKeyId: String? = null,
    @SerialName("docReferrer")
    var docReferrer: String? = null,
    @SerialName("isPassEmpty")
    var isPassEmpty: Boolean? = null,
    @SerialName("isSocial")
    var isSocial: Boolean? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null,
    @SerialName("sso")
    var sso: Sso? = null,
    @SerialName("userCreated")
    var userCreated: Boolean? = null
)


@Serializable
data class Sso(
    @SerialName("social")
    var social: String? = null,
    @SerialName("type")
    var type: String? = null
)
package com.kompasid.netdatalibrary.core.data.otp.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class VerifyOTPResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: VerifyOTPResponseData? = null,
    @SerialName("message")
    var message: String? = null,
)

@Serializable
data class VerifyOTPResponseData(
    @SerialName("accessToken")
    var accessToken: String? = null,
    @SerialName("deviceKeyId")
    var deviceKeyId: String? = null,
    @SerialName("docReferrer")
    var docReferrer: String? = null,
    @SerialName("isComplete")
    var isComplete: Boolean? = null,
    @SerialName("isPassEmpty")
    var isPassEmpty: Boolean? = null,
    @SerialName("isSocial")
    var isSocial: Boolean? = null,
    @SerialName("isVIP")
    var isVIP: Boolean? = null,
    @SerialName("isVerified")
    var isVerified: Boolean? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null
)

package com.kompasid.netdatalibrary.core.data.otp.dto.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SendOTPResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: SendOTPResponseData? = null,
    @SerialName("message")
    var message: String? = null,
)

@Serializable
data class SendOTPResponseData(
    @SerialName("countdown")
    var countdown: Int? = null,
    @SerialName("remainingOtp")
    var remainingOtp: Int? = null
)




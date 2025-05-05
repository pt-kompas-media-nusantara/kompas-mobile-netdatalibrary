package com.kompasid.netdatalibrary.core.data.otp.dto.request
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class VerifyOTPRequest(
    @SerialName("countryCode")
    var countryCode: String,
    @SerialName("device")
    var device: String,
    @SerialName("deviceType")
    var deviceType: String,
    @SerialName("docReferrer")
    var docReferrer: String,
    @SerialName("otp")
    var otp: String,
    @SerialName("phoneNumber")
    var phoneNumber: String
)



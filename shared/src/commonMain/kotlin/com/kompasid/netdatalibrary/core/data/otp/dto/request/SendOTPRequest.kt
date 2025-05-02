package com.kompasid.netdatalibrary.core.data.otp.dto.request
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class SendOTPRequest(
    @SerialName("countryCode")
    var countryCode: String,
    @SerialName("flag")
    var flag: Int,
    @SerialName("phoneNumber")
    var phoneNumber: String
)


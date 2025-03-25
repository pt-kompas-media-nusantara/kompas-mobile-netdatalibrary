package com.kompasid.netdatalibrary.core.data.register.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("email")
    var email: String,
    @SerialName("firstName")
    var firstName: String,
    @SerialName("lastName")
    var lastName: String,
    @SerialName("password")
    var password: String,
    @SerialName("device")
    var device: String,
    @SerialName("deviceType")
    var deviceType: String,
    @SerialName("docReferrer")
    var docReferrer: String,
)

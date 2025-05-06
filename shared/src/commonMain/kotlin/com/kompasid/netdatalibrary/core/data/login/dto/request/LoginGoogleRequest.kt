package com.kompasid.netdatalibrary.core.data.login.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class LoginGoogleRequest(
    @SerialName("accessToken")
    var accessToken: String,
    @SerialName("device")
    var device: String,
    @SerialName("deviceType")
    var deviceType: String,
    @SerialName("docReferrer")
    var docReferrer: String,
    @SerialName("state")
    var state: String
)


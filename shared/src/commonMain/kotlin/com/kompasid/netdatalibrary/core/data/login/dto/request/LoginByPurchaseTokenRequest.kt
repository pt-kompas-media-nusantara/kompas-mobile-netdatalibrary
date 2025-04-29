package com.kompasid.netdatalibrary.core.data.login.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginByPurchaseTokenRequest(
    @SerialName("token")
    var token: String,
)
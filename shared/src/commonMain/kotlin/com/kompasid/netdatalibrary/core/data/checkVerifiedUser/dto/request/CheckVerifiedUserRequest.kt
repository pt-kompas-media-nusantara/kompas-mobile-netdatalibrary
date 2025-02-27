package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class CheckVerifiedUserRequest(
    @SerialName("type")
    var type: String? = null,
    @SerialName("value")
    var value: String? = null
)


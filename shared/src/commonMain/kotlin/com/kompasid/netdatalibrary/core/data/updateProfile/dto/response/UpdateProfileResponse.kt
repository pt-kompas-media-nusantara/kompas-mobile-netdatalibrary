package com.kompasid.netdatalibrary.core.data.updateProfile.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class UpdateProfileResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("message")
    var message: String? = null,
)



package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class CheckRegisteredUsersRequest(
    @SerialName("type")
    var type: String? = null,
    @SerialName("value")
    var value: String? = null
)


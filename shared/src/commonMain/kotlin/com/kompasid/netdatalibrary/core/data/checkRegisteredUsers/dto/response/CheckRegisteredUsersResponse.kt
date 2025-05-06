package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class CheckVerifiedUserResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: CheckRegisteredUsersData? = null,
    @SerialName("message")
    var message: String? = null,
)

@Serializable
data class CheckRegisteredUsersData(
    @SerialName("registered")
    var registered: Boolean? = null,
    @SerialName("registeredBy")
    var registeredBy: String? = null,
    @SerialName("registeredOn")
    var registeredOn: List<String?>? = null
)



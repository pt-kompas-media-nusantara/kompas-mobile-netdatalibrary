package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class CheckVerifiedUserResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: CheckVerifiedUserResponseData? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("meta")
    var meta: Meta? = null,
    @SerialName("success")
    var success: Boolean? = null
)

@Serializable
data class CheckVerifiedUserResponseData(
    @SerialName("registered")
    var registered: Boolean? = null,
    @SerialName("registeredBy")
    var registeredBy: String? = null,
//    @SerialName("registeredOn")
//    var registeredOn: List<Any?>? = null
)

@Serializable
data class Meta(
    @SerialName("cache")
    var cache: Boolean? = null,
    @SerialName("time")
    var time: Int? = null
)



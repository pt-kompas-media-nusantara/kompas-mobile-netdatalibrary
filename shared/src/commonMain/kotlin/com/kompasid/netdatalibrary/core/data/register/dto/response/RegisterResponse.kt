package com.kompasid.netdatalibrary.core.data.register.dto.response

import com.kompasid.netdatalibrary.core.data.login.dto.response.LoginResponseData
import com.kompasid.netdatalibrary.core.data.login.dto.response.Sso
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: RegisterResponseData? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("success")
    var success: Boolean? = null
)


@Serializable
data class RegisterResponseData(
    @SerialName("accessToken")
    var accessToken: String? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null,
    @SerialName("passwordCheck")
    var passwordCheck: String? = null,
    @SerialName("registeredOn")
    var registeredOn: List<String?>? = null
)


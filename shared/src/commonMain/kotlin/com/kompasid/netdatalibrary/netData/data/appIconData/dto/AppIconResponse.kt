package com.kompasid.netdatalibrary.netData.data.appIconData.dto
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class AppIconResponse(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("result")
    val result: Result? = null,
    @SerialName("status")
    val status: String? = null,
)

@Serializable
data class Result(
    @SerialName("android")
    val android: Android? = null,
    @SerialName("iOS")
    val iOS: IOS? = null,
    @SerialName("mrw_quota_anon")
    val mrwQuotaAnon: Int? = null,
    @SerialName("mrw_quota_regon")
    val mrwQuotaRegon: Int? = null
)

@Serializable
data class Android(
    @SerialName("logo")
    val logo: List<Logo?>? = null
)

@Serializable
data class IOS(
    @SerialName("logo")
    val logo: List<Logo>? = null,
)

@Serializable
data class Logo(
    @SerialName("key")
    val key: String? = null,
    @SerialName("value")
    val value: String? = null
)



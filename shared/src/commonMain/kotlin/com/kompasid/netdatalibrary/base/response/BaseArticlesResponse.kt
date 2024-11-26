package com.kompasid.netdatalibrary.base.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseArticlesResponse<T>(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("message")
    val message: Message? = null,
    @SerialName("meta")
    val meta: Meta? = null,
    @SerialName("result")
    val result: List<T?>? = null
)

@Serializable
data class Message(
    @SerialName("message")
    val message: String? = null
)

@Serializable
data class Meta(
    @SerialName("cache")
    val cache: Boolean? = null,
    @SerialName("next")
    val next: Int? = null,
    @SerialName("site")
    val site: String? = null,
    @SerialName("total")
    val total: Int? = null,
    @SerialName("version")
    val version: Int? = null,
)
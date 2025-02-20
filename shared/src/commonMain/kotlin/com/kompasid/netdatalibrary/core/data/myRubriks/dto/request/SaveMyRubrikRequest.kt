package com.kompasid.netdatalibrary.core.data.myRubriks.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveMyRubrikRequest(
    @SerialName("type") // nurirppan_ : ini hardcode aja dari kmp
    val type: String,
    @SerialName("userRubriks")
    val userRubriks: List<UserRubrikRequest>
)

@Serializable
data class UserRubrikRequest(
    @SerialName("isChoosen")
    val isChoosen: Boolean,
    @SerialName("value")
    val value: String
)

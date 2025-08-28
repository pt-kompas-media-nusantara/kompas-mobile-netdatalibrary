package com.kompasid.netdatalibrary.core.data.myRubriks.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class SaveMyRubrikResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: SaveMyRubrikResponseData? = null,
    @SerialName("message")
    var message: String? = null,
)

@Serializable
data class SaveMyRubrikResponseData(
    @SerialName("type")
    var type: String? = null,
    @SerialName("userRubriks")
    var userRubriks: List<UserRubrik?>? = null
)

@Serializable
data class UserRubrik(
    @SerialName("isChoosen")
    var isChoosen: Boolean? = null,
    @SerialName("value")
    var value: String? = null
)




//data class SaveMyRubrikResponse(
//    val code: Int? = null,
//    val data: SaveMyRubrikData? = null,
//    val message: String? = null,
//)
//
//data class SaveMyRubrikData(
//    val type: String? = null,
//    val userRubriks: List<UserRubrik>? = null,
//)
//
//data class Meta(
//    val cache: Boolean? = null,
//    val time: Int? = null,
//)
//
//data class UserRubrik(
//    val isChoosen: Boolean? = null,
//    val value: String? = null,
//)
package com.kompasid.netdatalibrary.core.data.myRubriks.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class OldMyRubriksResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: List<OldMyRubriksResponseData?>? = null,
)

@Serializable
data class OldMyRubriksResponseData(
    @SerialName("banner")
    var banner: String? = null,
    @SerialName("desc")
    var desc: String? = null,
    @SerialName("isChoosen")
    var isChoosen: Boolean? = null,
    @SerialName("onCds")
    var onCds: Boolean? = null,
    @SerialName("text")
    var text: String? = null,
    @SerialName("url")
    var url: String? = null,
    @SerialName("urlImg")
    var urlImg: String? = null,
    @SerialName("value")
    var value: String? = null
)



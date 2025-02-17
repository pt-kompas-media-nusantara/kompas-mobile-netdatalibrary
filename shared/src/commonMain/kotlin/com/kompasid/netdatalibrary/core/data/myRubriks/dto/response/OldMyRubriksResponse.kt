package com.kompasid.netdatalibrary.core.data.myRubriks.dto.response

data class OldMyRubriksResponse(
    val code: Int? = null,
    val data: List<DataMyRubriksResponse>? = null,
    val message: String? = null
)

data class DataMyRubriksResponse(
    val banner: String? = null,
    val desc: String? = null,
    val isChoosen: Boolean? = null,
    val onCds: Boolean? = null,
    val text: String? = null,
    val url: String? = null,
    val urlImg: String? = null,
    val value: String? = null
)
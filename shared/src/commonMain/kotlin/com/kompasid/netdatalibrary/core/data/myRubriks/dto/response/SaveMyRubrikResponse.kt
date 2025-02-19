package com.kompasid.netdatalibrary.core.data.myRubriks.dto.response

data class SaveMyRubrikResponse(
    val code: Int? = null,
    val data: SaveMyRubrikData? = null,
    val message: String? = null,
)

data class SaveMyRubrikData(
    val type: String? = null,
    val userRubriks: List<UserRubrik>? = null,
)

data class Meta(
    val cache: Boolean? = null,
    val time: Int? = null,
)

data class UserRubrik(
    val isChoosen: Boolean? = null,
    val value: String? = null,
)
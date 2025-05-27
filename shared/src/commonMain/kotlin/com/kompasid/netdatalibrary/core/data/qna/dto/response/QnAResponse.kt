package com.kompasid.netdatalibrary.core.data.qna.dto.response

data class QnAResponse(
    val iOS: List<IOS>? = null
)

data class IOS(
    val answer: List<String>? = null,
    val id: String? = null,
    val question: String? = null
)


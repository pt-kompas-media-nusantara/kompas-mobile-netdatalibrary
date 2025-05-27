package com.kompasid.netdatalibrary.core.data.qna.dto.interceptor

data class QnAResInterceptor(
    val iOS: List<IOSResInterceptor>
)

data class IOSResInterceptor(
    val answer: List<String>,
    val id: String,
    val question: String
)

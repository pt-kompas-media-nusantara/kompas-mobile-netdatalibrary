package com.kompasid.netdatalibrary.core.data.qna.mappers

import com.kompasid.netdatalibrary.core.data.qna.dto.interceptor.IOSResInterceptor
import com.kompasid.netdatalibrary.core.data.qna.dto.interceptor.QnAResInterceptor
import com.kompasid.netdatalibrary.core.data.qna.dto.response.QnAResponse


fun QnAResponse.toInterceptor(): QnAResInterceptor {
    return QnAResInterceptor(
        iOS = iOS?.map {
            IOSResInterceptor(
                answer = it.answer ?: emptyList(),
                id = it.id.orEmpty(),
                question = it.question.orEmpty()
            )
        } ?: emptyList()
    )

}
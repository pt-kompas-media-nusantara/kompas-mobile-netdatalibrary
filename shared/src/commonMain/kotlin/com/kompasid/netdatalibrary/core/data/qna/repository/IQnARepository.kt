package com.kompasid.netdatalibrary.core.data.qna.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.qna.dto.interceptor.QnAResInterceptor

interface IQnARepository {
    suspend fun qna(): Results<QnAResInterceptor, NetworkError>
}
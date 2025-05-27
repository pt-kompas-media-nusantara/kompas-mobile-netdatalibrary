package com.kompasid.netdatalibrary.core.data.qna.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.qna.dto.response.QnAResponse

interface IQnAApiService {
    suspend fun qna(): ApiResults<QnAResponse, NetworkError>
}

package com.kompasid.netdatalibrary.core.data.qna.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.qna.dto.interceptor.QnAResInterceptor
import com.kompasid.netdatalibrary.core.data.qna.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.qna.network.QnAApiService
import com.kompasid.netdatalibrary.core.data.qna.resultState.QnAResultState





class QnARepository(
    private val qnAApiService: QnAApiService,
    private val qnAResultState: QnAResultState
) : IQnARepository {

    override suspend fun qna(): Results<QnAResInterceptor, NetworkError> {
        return when (val result = qnAApiService.qna()) {
            is ApiResults.Success -> {
                result.data.toInterceptor().also { resultInterceptor ->

                    qnAResultState.apply {
                        if (qna.value != resultInterceptor) update(
                            resultInterceptor
                        )
                    }
                }.let { Results.Success(it) }
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}
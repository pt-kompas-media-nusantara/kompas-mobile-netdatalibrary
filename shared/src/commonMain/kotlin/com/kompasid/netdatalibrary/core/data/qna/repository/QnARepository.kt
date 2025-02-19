package com.kompasid.netdatalibrary.core.data.qna.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.dataSource.MyRubriksDataSource
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.network.MyRubriksApiService
import com.kompasid.netdatalibrary.core.data.myRubriks.repository.IMyRubriksRepository
import com.kompasid.netdatalibrary.core.data.myRubriks.resultState.MyRubriksResultState
import com.kompasid.netdatalibrary.core.data.qna.dto.interceptor.QnAResInterceptor
import com.kompasid.netdatalibrary.core.data.qna.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.qna.network.QnAApiService
import com.kompasid.netdatalibrary.core.data.qna.resultState.QnAResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow





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
package com.kompasid.netdatalibrary.core.data.qna.resultState

import com.kompasid.netdatalibrary.core.data.qna.dto.interceptor.QnAResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QnAResultState {
    private val _qna = MutableStateFlow<QnAResInterceptor?>(null)
    var qna: StateFlow<QnAResInterceptor?> = _qna.asStateFlow()

    suspend fun update(data: QnAResInterceptor) {
        _qna.value = data
    }

}
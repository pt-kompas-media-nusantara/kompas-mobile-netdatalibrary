package com.kompasid.netdatalibrary.core.domain.manageAccount.resultState

import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ManageAccountState() {
    private val _myRubriks = MutableStateFlow<List<MyRubriksResInterceptor>>(emptyList())
    var myRubriks: StateFlow<List<MyRubriksResInterceptor>> = _myRubriks.asStateFlow()

    fun updateMyRubriks(data: List<MyRubriksResInterceptor>) {
        _myRubriks.value = data
    }
}
package com.kompasid.netdatalibrary.core.data.myRubriks.resultState

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyRubriksResultState(
    private val settingsHelper: SettingsHelper
) {
    private val _allRubriks = MutableStateFlow<List<MyRubriksResInterceptor>>(emptyList())
    var allRubriks: StateFlow<List<MyRubriksResInterceptor>> = _allRubriks.asStateFlow()

    private val _myRubriks = MutableStateFlow<List<MyRubriksResInterceptor>>(emptyList())
    var myRubriks: StateFlow<List<MyRubriksResInterceptor>> = _myRubriks.asStateFlow()

    suspend fun updateAllRubriks(data: List<MyRubriksResInterceptor>) {
        _allRubriks.value = data
    }

    suspend fun updateMyRubriks(data: List<MyRubriksResInterceptor>) {
        _myRubriks.value = data
        settingsHelper.save(KeySettingsType.MY_RUBRIKS, data)
    }
}
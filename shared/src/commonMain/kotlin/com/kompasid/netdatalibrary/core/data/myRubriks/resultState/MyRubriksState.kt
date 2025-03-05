package com.kompasid.netdatalibrary.core.data.myRubriks.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyRubriksState: BaseVM() {
    private val _allRubriks = MutableStateFlow<List<MyRubriksResInterceptor>>(emptyList())
    val allRubriks: StateFlow<List<MyRubriksResInterceptor>> = _allRubriks.asStateFlow()

    private val _myRubriks = MutableStateFlow<List<MyRubriksResInterceptor>>(emptyList())
    val myRubriks: StateFlow<List<MyRubriksResInterceptor>> = _myRubriks.asStateFlow()

    fun updateAllRubriks(data: List<MyRubriksResInterceptor>) {
        _allRubriks.update { data }
    }

    fun updateMyRubriks(data: List<MyRubriksResInterceptor>) {
        _myRubriks.update { data }
    }

    fun streamAllRubriks(): Flow<List<MyRubriksResInterceptor>> =
        allRubriks.shareIn(scope, SharingStarted.Eagerly, replay = 1)

    fun streamMyRubriks(): Flow<List<MyRubriksResInterceptor>> =
        myRubriks.shareIn(scope, SharingStarted.Eagerly, replay = 1)
}

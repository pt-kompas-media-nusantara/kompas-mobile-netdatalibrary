package com.kompasid.netdatalibrary.core.domain.personalInfo.resultState

import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PersonalInfoState {
    private val _state: MutableStateFlow<PersonalInfoInterceptor> =
        MutableStateFlow(PersonalInfoInterceptor())

    private val state: StateFlow<PersonalInfoInterceptor> = _state.asStateFlow()

    fun streamPersonalInfo(): Flow<PersonalInfoInterceptor> = channelFlow {
        val job = launch {
            state.collectLatest { newData ->
                send(newData)
            }
        }
        awaitClose { job.cancel() } // Menutup stream saat tidak digunakan
    }

    suspend fun updatePersonalInfo(newData: PersonalInfoInterceptor) {
        _state.value = newData
    }
}
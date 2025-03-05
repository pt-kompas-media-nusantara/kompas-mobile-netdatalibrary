package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CheckVerifiedUserResultState() : BaseVM() {
    private val _checkVerifiedUser: MutableStateFlow<CheckVerifiedUserResInterceptor> =
        MutableStateFlow(CheckVerifiedUserResInterceptor())
    val checkVerifiedUser: StateFlow<CheckVerifiedUserResInterceptor> get() = _checkVerifiedUser

    fun logger() {
        scope.launch {
            Logger.info {
                "${_checkVerifiedUser.value}"
            }
        }
    }

}


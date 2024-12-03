package com.kompasid.netdatalibrary.netData.presentation.appIconPresentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.NetworkVM.NetworkVM
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.netData.domain.appIconDomain.AppIconResInterceptor
import com.kompasid.netdatalibrary.netData.domain.appIconDomain.AppIconUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppIconVM(
    private val appIconUseCase: AppIconUseCase,
    private val networkVM: NetworkVM
) : BaseVM() {

    private val _data = MutableStateFlow<AppIconResInterceptor?>(null)
    val data: StateFlow<AppIconResInterceptor?> get() = _data

    fun appIcon() {
        scope.launch {
            val result = appIconUseCase.appIcon()
            when (result) {
                is Results.Error -> {
                    networkVM.statusToError(result.error)
                }

                is Results.Success -> {
                    val interceptor = result.data
                    _data.value = result.data
                    Logger.debug { interceptor.toString() }
                }
            }
        }
    }
}
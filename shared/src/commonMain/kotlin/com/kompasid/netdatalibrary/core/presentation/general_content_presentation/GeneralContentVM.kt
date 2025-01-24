package com.kompasid.netdatalibrary.core.presentation.general_content_presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.NetworkVM.NetworkVM
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.GeneralContentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GeneralContentVM(
    private val useCase: GeneralContentUseCase,
    private val networkVM: NetworkVM
) : BaseVM() {

    private val _data = MutableStateFlow<GeneralContentInterceptor?>(null)
    val data: StateFlow<GeneralContentInterceptor?> get() = _data

    fun getGeneralData() {
        scope.launch {
            when (val result = useCase.getGeneralData()) {
                is Results.Error -> {
                    networkVM.statusToError(result.error)
                }

                is Results.Success -> {
                    val interceptor = result.data
                    _data.update {
                        interceptor
                    }
                    Logger.debug { interceptor.toString() }
                }
            }
        }
    }
}
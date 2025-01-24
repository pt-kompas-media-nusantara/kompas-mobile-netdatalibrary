package com.kompasid.netdatalibrary.core.presentation.accountPresentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.domain.myAccount.usecase.MyAccountUseCase
import kotlinx.coroutines.launch

class AccountVM(
    private val myAccountUseCase: MyAccountUseCase,
) : BaseVM() {


    fun appIcon() {
        scope.launch {
            val result = myAccountUseCase.suberAccountMenu()
//            when (result) {
//                is Results.Error -> {
//                    networkVM.statusToError(result.error)
//                }
//
//                is Results.Success -> {
//                    val interceptor = result.data
//                    _data.value = result.data
//                    Logger.debug { interceptor.toString() }
//                }
//            }
        }
    }
}
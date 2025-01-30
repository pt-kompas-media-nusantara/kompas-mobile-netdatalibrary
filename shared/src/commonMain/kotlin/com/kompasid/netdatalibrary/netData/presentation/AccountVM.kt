package com.kompasid.netdatalibrary.netData.presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.MyAccountUseCase
import kotlinx.coroutines.launch

class AccountVM(
    private val myAccountUseCase: MyAccountUseCase,
) : BaseVM() {


    fun appIcon() {
        scope.launch {
            val result = myAccountUseCase.accountMenus()
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
//myAccountUseCase.myAccountInformation()
//myAccountUseCase.isAccountSubcriber()
//myAccountUseCase.stateLoginUser()
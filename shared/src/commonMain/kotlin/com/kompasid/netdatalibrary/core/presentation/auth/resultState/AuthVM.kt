package com.kompasid.netdatalibrary.core.presentation.auth.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.launch

class AuthVM(
    private val authUseCase: AuthUseCase,
    private val settingsHelper: SettingsHelper
) : BaseVM() {


    fun removaAllTest() {
        scope.launch {
            settingsHelper.removeAll()
        }
    }

    fun loginByEmailTest() {
//        scope.launch {
//            val result = authUseCase.loginByEmail(
//                LoginEmailRequest(
//                    "nur.irfan@kompas.com",
//                    "Nurirppankompas@28",
//                    "testKMP",
//                    "testKMP",
//                )
//            )
//            when (result) {
//                is Results.Error -> {
//                    Logger.error {
//                        result.error.toString()
//                    }
//                }
//
//                is Results.Success -> {
//                    // Mendapatkan data dari Pair
//                }
//            }
//        }
    }
}
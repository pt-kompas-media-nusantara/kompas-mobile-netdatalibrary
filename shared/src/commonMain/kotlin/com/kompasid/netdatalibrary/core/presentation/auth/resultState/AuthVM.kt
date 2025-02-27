package com.kompasid.netdatalibrary.core.presentation.auth.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.interceptor.LoginInterceptor
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.resultState.LoginResultState
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.resultState.UserDetailResultState
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.resultState.UserHistoryMembershipResultState
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthVM(
    private val authUseCase: AuthUseCase,
    private val loginResultState: LoginResultState,
    private val userDetailResultState: UserDetailResultState,
    private val userHistoryMembershipResultState: UserHistoryMembershipResultState,
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    val login = loginResultState.data
    val userDetail = userDetailResultState.data
    val userHistoryMembership = userHistoryMembershipResultState.data

    fun removaAllTest() {
        scope.launch {
            settingsHelper.removeAll()
        }
    }

    fun loginByEmailTest() {
        scope.launch {
            val result = authUseCase.loginByEmail(
                LoginEmailRequest(
                    "nur.irfan@kompas.com",
                    "Nurirppankompas@28",
                    "testKMP",
                    "testKMP",
                )
            )
            when (result) {
                is Results.Error -> {
                    Logger.error {
                        result.error.toString()
                    }
                }

                is Results.Success -> {
                    // Mendapatkan data dari Pair
                    val (unitData, userData) = result.data
                    val (userDetails, userHistory) = userData

//                    Logger.debug {
//                        unitData.toString()
//                    }
//                    Logger.debug {
//                        userDetails.toString()
//                    }
//                    Logger.debug {
//                        userHistory.toString()
//                    }
                }
            }
        }
    }
}
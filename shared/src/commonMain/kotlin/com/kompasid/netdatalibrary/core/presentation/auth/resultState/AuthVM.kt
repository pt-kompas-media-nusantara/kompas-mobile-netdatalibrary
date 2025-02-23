package com.kompasid.netdatalibrary.core.presentation.auth.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginResultState
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.resultState.UserDetailResultState
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.resultState.UserHistoryMembershipResultState
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import kotlinx.coroutines.flow.StateFlow

class AuthVM(
    private val authUseCase: AuthUseCase,
    private val loginResultState: LoginResultState,
    private val userDetailResultState: UserDetailResultState,
    private val userHistoryMembershipResultState: UserHistoryMembershipResultState,
) : BaseVM() {
    val loginInterceptor: StateFlow<LoginInterceptor> =
        loginResultState.loginInterceptor
    val userDetailResInterceptor: StateFlow<UserDetailResInterceptor> =
        userDetailResultState.userDetailState
    val userHistoryMembershipResInterceptor: StateFlow<UserHistoryMembershipResInterceptor> =
        userHistoryMembershipResultState.userHistoryMembershipResInterceptor

    suspend fun loginByEmail() {
        val result = authUseCase.loginByEmail(
            LoginEmailRequest(
                "nur.irfan@kompas.com",
                "Nurirppankompas@28",
                "testKMP",
                "testKMP",
            )
        )

        when (result) {
            is Results.Success -> {
                println("Success")
            }

            is Results.Error -> {
                when (val error = result.error) {
                    is NetworkError.RequestTimeout -> {
                        println("Request timeout occurred")
                    }

                    is NetworkError.Unauthorized -> {
                        println("Unauthorized access")
                    }

                    is NetworkError.NoInternet -> {
                        println("No internet connection")
                    }

                    is NetworkError.ServerError -> {
                        println("Server error occurred")
                    }

                    is NetworkError.NotFound -> {
                        println("Resource not found")
                    }

                    is NetworkError.Technical -> {
                        println("Technical error: Code ${error.code}, Message: ${error.message}")
                    }

                    is NetworkError.Error -> {
                        println("Error: ${error.throwable.message}")
                    }

                    else -> {
                        println("Unknown error occurred")
                    }
                }
            }


        }
    }
}
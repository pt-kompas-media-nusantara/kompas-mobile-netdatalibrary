package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginEmailRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginGuest.repository.LoginGuestRepository
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor

interface LoginGuestRepositoryContoh {
    suspend fun postLoginGuest(): Results<Unit, NetworkError>
}

class AuthUseCase(
    private val loginGuestRepository: LoginGuestRepository,
    private val loginEmailRepository: LoginEmailRepository,
    private val logoutRepository: LogoutRepository,
    private val personalInfoUseCase: PersonalInfoUseCase
) {

    suspend fun loginAnon(): Results<Unit, NetworkError> {
        val result = loginGuestRepository.postLoginGuest()
        return result
    }

    suspend fun loginByEmail(request: LoginEmailRequest): Results<Pair<Unit, Pair<UserDetailResInterceptor, UserHistoryMembershipResInterceptor>>, NetworkError> {
        val loginResult = loginEmailRepository.postLoginEmail(request)

        when (loginResult) {
            is Results.Success -> {
                val userDetailsAndMembershipResult =
                    personalInfoUseCase.getUserDetailsAndMembership()

                when (userDetailsAndMembershipResult) {
                    is Results.Success -> {
                        return Results.Success(
                            Pair(
                                loginResult.data,
                                Pair(
                                    userDetailsAndMembershipResult.data.first,
                                    userDetailsAndMembershipResult.data.second
                                )
                            )
                        )
                    }

                    is Results.Error -> {
                        return Results.Error(userDetailsAndMembershipResult.error)
                    }
                }
            }

            is Results.Error -> {
                return Results.Error(loginResult.error)
            }
        }
    }

    suspend fun logout(): Results<Unit, NetworkError> {
        val result = logoutRepository.postLogout()
        return result
    }


}

data class LoginAnonResInterceptor(
    val userId: String
)
package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginEmailRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginGuest.repository.LoginGuestRepository
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

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
        return try {
            val loginResult = loginEmailRepository.postLoginEmail(request)

            when (loginResult) {
                is Results.Success -> {
                    coroutineScope {
                        val userDetailsDeferred = async { personalInfoUseCase.getUserDetailsAndMembership() }

                        try {
                            val userDetailsAndMembershipResult = userDetailsDeferred.await()

                            when (userDetailsAndMembershipResult) {
                                is Results.Success -> Results.Success(
                                    Pair(
                                        loginResult.data,
                                        Pair(userDetailsAndMembershipResult.data.first, userDetailsAndMembershipResult.data.second)
                                    )
                                )
                                is Results.Error -> {
                                    userDetailsDeferred.cancel() // Batalkan jika gagal
                                    Results.Error(userDetailsAndMembershipResult.error)
                                }
                            }
                        } catch (e: Exception) {
                            userDetailsDeferred.cancel()
                            Results.Error(NetworkError.Error(e))
                        }
                    }
                }

                is Results.Error -> {
                    Results.Error(loginResult.error)
                }
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
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
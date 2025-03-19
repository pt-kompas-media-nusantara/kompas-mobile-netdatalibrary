package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginEmailRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope


class AuthUseCase(
    private val loginEmailRepository: LoginEmailRepository,
    private val logoutRepository: LogoutRepository,
    private val personalInfoUseCase: PersonalInfoUseCase
) {

    suspend fun loginByEmail(request: LoginEmailRequest): Results<String, NetworkError> =
        supervisorScope {
            try {
                val loginResult = loginEmailRepository.loginByEmail(request)

                if (loginResult is Results.Error) {
                    return@supervisorScope Results.Error(loginResult.error)
                }

                val userDetailsDeferred = async { personalInfoUseCase.getUserDetailsAndMembership() }

                val userDetailsAndMembershipResult = userDetailsDeferred.await()

                when (userDetailsAndMembershipResult) {
                    is Results.Success -> Results.Success(userDetailsAndMembershipResult.data.first.email)
                    is Results.Error -> Results.Error(userDetailsAndMembershipResult.error)
                }
            } catch (exception: Exception) {
                Results.Error(NetworkError.Error(exception))
            }
        }

    suspend fun logout(): Results<Unit, NetworkError> {
        return try {
            logoutRepository.postLogout()
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }
}

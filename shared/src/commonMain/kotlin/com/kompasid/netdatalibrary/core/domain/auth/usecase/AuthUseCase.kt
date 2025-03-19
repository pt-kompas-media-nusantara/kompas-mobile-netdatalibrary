package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginEmailRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class AuthUseCase(
    private val loginEmailRepository: LoginEmailRepository,
    private val logoutRepository: LogoutRepository,
    private val personalInfoUseCase: PersonalInfoUseCase
) {

    // nurirppan__ :
    suspend fun loginByEmail(request: LoginEmailRequest): Results<Unit, NetworkError> =
        runCatching {
            val loginResult = loginEmailRepository.loginByEmail(request)

            if (loginResult is Results.Error) return Results.Error(loginResult.error)

            coroutineScope {
                val userDetailsAndMembershipResult =
                    async { personalInfoUseCase.getUserDetailsAndMembership() }.await()

                when (userDetailsAndMembershipResult) {
                    is Results.Success -> Results.Success(Unit)
                    is Results.Error -> Results.Error(userDetailsAndMembershipResult.error)
                }
            }
        }.getOrElse { exception ->
            Results.Error(NetworkError.Error(exception))
        }


    suspend fun logout(): Results<Unit, NetworkError> = runCatching {
        logoutRepository.postLogout()
    }.getOrElse { exception ->
        Results.Error(NetworkError.Error(exception))
    }


}

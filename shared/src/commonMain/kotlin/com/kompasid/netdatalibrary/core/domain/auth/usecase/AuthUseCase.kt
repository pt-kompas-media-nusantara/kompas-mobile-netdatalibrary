package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginAppleRequest
import com.kompasid.netdatalibrary.core.data.login.repository.LoginRepository
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginGoogleRequest
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope


class AuthUseCase(
    private val loginRepository: LoginRepository,
    private val logoutRepository: LogoutRepository,
    private val personalInfoUseCase: PersonalInfoUseCase
) {

    suspend fun loginByEmail(request: LoginEmailRequest): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByEmail(request)
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByGoogle(request: LoginGoogleRequest): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByGoogle(request)
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByApple(request: LoginAppleRequest): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByApple(request)
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

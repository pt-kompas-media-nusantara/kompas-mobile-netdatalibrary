package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.repository.CheckRegisteredUsersRepository
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginAppleRequest
import com.kompasid.netdatalibrary.core.data.login.repository.LoginRepository
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginGoogleRequest
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.helpers.logged


class AuthUseCase(
    private val loginRepository: LoginRepository,
    private val checkRegisteredUsersRepository: CheckRegisteredUsersRepository,
    private val logoutRepository: LogoutRepository
) {

    suspend fun checkRegisteredUsers(value: String): Results<CheckRegisteredUsersResInterceptor, NetworkError> {
        return try {
            checkRegisteredUsersRepository.checkRegisteredUsers(value).logged(prefix = "checkRegisteredUsers")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByEmail(request: LoginEmailRequest): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByEmail(request).logged(prefix = "loginByEmail")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByGoogle(request: LoginGoogleRequest): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByGoogle(request).logged(prefix = "loginByGoogle")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByApple(request: LoginAppleRequest): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByApple(request).logged(prefix = "loginByApple")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun logout(): Results<Unit, NetworkError> {
        return try {
            logoutRepository.postLogout().logged(prefix = "logout")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }
}


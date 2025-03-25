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

    suspend fun loginByEmail(email: String, password: String): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByEmail(email, password).logged(prefix = "loginByEmail")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByGoogle(accessToken: String, state: String): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByGoogle(accessToken, state).logged(prefix = "loginByGoogle")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByApple(accessToken: String): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByApple(accessToken).logged(prefix = "loginByApple")
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


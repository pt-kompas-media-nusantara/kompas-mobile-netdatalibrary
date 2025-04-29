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
import com.kompasid.netdatalibrary.core.data.register.dto.interceptor.RegisterResInterceptor
import com.kompasid.netdatalibrary.core.data.register.repository.RegisterRepository
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.enums.AuthFlowType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.logged
import kotlinx.coroutines.async


class AuthUseCase(
    private val checkRegisteredUsersRepository: CheckRegisteredUsersRepository,
    private val loginRepository: LoginRepository,
    private val registerRepository: RegisterRepository,
    private val logoutRepository: LogoutRepository,
    private val settingsHelper: SettingsHelper,
    private val supportSettingsHelper: SupportSettingsHelper
) {

    // login by phone number
    // figma: https://www.figma.com/design/Ujy2qXggVShfFcem2LWXuD/Option-OTP-via-SMS?node-id=90-21995&t=LjdnjJVXt3nMNhj8-0
    suspend fun sendOTPToWhatsApp() {

    }

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

    suspend fun registerByEmail(email: String, firstName: String, lastName: String, password: String): Results<RegisterResInterceptor, NetworkError> {
        return try {
            registerRepository.registerByEmail(email, firstName, lastName, password).logged(prefix = "registerByEmail")
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
//multiple sso : https://www.figma.com/design/6mp9LQv56mUAqKXDaO60on/iOS---Akun---Misc?node-id=6191-428951&p=f&t=sPIjsZp9TanIzOFY-0
//login by whatsapp : https://www.figma.com/design/Ujy2qXggVShfFcem2LWXuD/Option-OTP-via-SMS?node-id=90-21995&t=LjdnjJVXt3nMNhj8-0

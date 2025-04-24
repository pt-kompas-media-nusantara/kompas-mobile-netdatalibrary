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


class AuthUseCase(
    private val checkRegisteredUsersRepository: CheckRegisteredUsersRepository,
    private val loginRepository: LoginRepository,
    private val registerRepository: RegisterRepository,
    private val logoutRepository: LogoutRepository,
    private val settingsHelper: SettingsHelper,
    private val supportSettingsHelper: SupportSettingsHelper
) {

    //    (hit api dulu kalau begitu, untuk mengecek apakah apulo yang muncul atau auto login)
//    - jika user mempunyai purchase token baik aktif maupun tidak aktif akan menampilkan auto login.
//    - jika user tidak mempunyai purchase token akan menampilkan halaman login / register / berlangganan tergantung entry point yang di click
//    - jika user regon dan mempunyai purchase token aktif, dimana purchase token tersebut tidak mempunyai guid. maka akan menampilkan apulo
    suspend fun checkAuthScreenType(): AuthFlowType {
        val email: String = settingsHelper.get(KeySettingsType.EMAIL, "")
        val originalTransactionId: List<String> = settingsHelper.get(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList())

        if (originalTransactionId.isNotEmpty()) {
            return AuthFlowType.AUTO_LOGIN
        }
        if (email.isNotEmpty() && originalTransactionId.isNotEmpty() && ) {
            return AuthFlowType.APULO
        }

//        if (isActive.lowercase() == suber.lowercase()) {
//            return AuthFlowType.NEXT
//        } else if (isActive.lowercase() != suber.lowercase()) {
////            && originalTransactionId.isNotEmpty()
//            // nurirppan : harusnya pas membershipnya active bukan kosong
//            return AuthFlowType.NEXT
//        } else {
//
//        }
            return AuthFlowType.NEXT
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


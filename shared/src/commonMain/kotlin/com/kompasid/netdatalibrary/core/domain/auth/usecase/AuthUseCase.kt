package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.repository.CheckRegisteredUsersRepository
import com.kompasid.netdatalibrary.core.data.login.repository.LoginRepository
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.SendOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.VerifyOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.repository.OTPRepository
import com.kompasid.netdatalibrary.core.data.register.dto.interceptor.RegisterResInterceptor
import com.kompasid.netdatalibrary.core.data.register.repository.RegisterRepository
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.logged


class AuthUseCase(
    private val checkRegisteredUsersRepository: CheckRegisteredUsersRepository,
    private val loginRepository: LoginRepository,
    private val registerRepository: RegisterRepository,
    private val logoutRepository: LogoutRepository,
    private val OTPRepository: OTPRepository,
    private val settingsHelper: SettingsHelper,
    private val supportSettingsHelper: SupportSettingsHelper
) {

    // login by phone number
    // figma: https://www.figma.com/design/Ujy2qXggVShfFcem2LWXuD/Option-OTP-via-SMS?node-id=90-21995&t=LjdnjJVXt3nMNhj8-0
    suspend fun sendOTP(countryCode: String, phoneNumber: String): Results<SendOTPResInterceptor, NetworkError> {
        return try {
            OTPRepository.sendOTP(countryCode, phoneNumber).logged(prefix = "UseCase: sendOTP")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun verifyOTP(countryCode: String, phoneNumber: String, otp: String): Results<VerifyOTPResInterceptor, NetworkError> {
        return try {
            OTPRepository.verifyOTP(countryCode, phoneNumber, otp).logged(prefix = "UseCase: verifyOTP")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    // fungsi ini digunakan untuk mengecek apakah email atau phone number yang di masukkan sudah terdaftar atau belum
    // dan juga mengarahkan ke halaman input password, multiple sso, atau send kode OTP
    suspend fun checkRegisteredUsers(value: String): Results<CheckRegisteredUsersResInterceptor, NetworkError> {
        return try {
            checkRegisteredUsersRepository.checkRegisteredUsers(value).logged(prefix = "UseCase: checkRegisteredUsers")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByEmail(email: String, password: String): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByEmail(email, password).logged(prefix = "UseCase: loginByEmail")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    // accessTokenByGoogle: The Access Token from Google.
    // idTokenByGoogle: The ID Token from Google.
    suspend fun loginByGoogle(accessTokenByGoogle: String, idTokenByGoogle: String): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByGoogle(accessTokenByGoogle, idTokenByGoogle).logged(prefix = "UseCase: loginByGoogle")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByApple(accessTokenByApple: String): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByApple(accessTokenByApple).logged(prefix = "UseCase: loginByApple")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun loginByPurchaseToken(): Results<Unit, NetworkError> {
        return try {
            loginRepository.loginByPurchaseToken().logged(prefix = "UseCase: loginByPurchaseToken")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    // nurirppan_ : beluman(cek lagi)
    suspend fun registerByEmail(email: String, firstName: String, lastName: String, password: String): Results<RegisterResInterceptor, NetworkError> {
        return try {
            registerRepository.registerByEmail(email, firstName, lastName, password).logged(prefix = "UseCase: registerByEmail")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun logout(): Results<Unit, NetworkError> {
        return try {
            logoutRepository.postLogout().logged(prefix = "UseCase: logout")
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }
}
//multiple sso : https://www.figma.com/design/6mp9LQv56mUAqKXDaO60on/iOS---Akun---Misc?node-id=6191-428951&p=f&t=sPIjsZp9TanIzOFY-0
//login by whatsapp : https://www.figma.com/design/Ujy2qXggVShfFcem2LWXuD/Option-OTP-via-SMS?node-id=90-21995&t=LjdnjJVXt3nMNhj8-0

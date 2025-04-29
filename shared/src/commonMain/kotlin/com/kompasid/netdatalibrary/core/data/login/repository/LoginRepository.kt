package com.kompasid.netdatalibrary.core.data.login.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.login.dataSource.LoginEmailDataSource
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginAppleRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginGoogleRequest
import com.kompasid.netdatalibrary.core.data.login.dto.response.LoginResponseData
import com.kompasid.netdatalibrary.core.data.login.dto.response.Sso
import com.kompasid.netdatalibrary.core.data.login.network.LoginApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class LoginRepository(
    private val loginApiService: LoginApiService,
    private val loginEmailDataSource: LoginEmailDataSource,
) : ILoginRepository {

    suspend fun loginByEmail(email: String, password: String): Results<Unit, NetworkError> {
        return try {
            when (val result = loginApiService.loginByEmail(email, password)) {
                is ApiResults.Success -> {
                    result.data.data?.let { data ->
                        coroutineScope {
                            val loginTask = async { saveLoginData(data) }
                            val ssoTask = async { saveSsoData(data.sso) }

                            // Tunggu keduanya selesai
                            loginTask.await()
                            ssoTask.await()
                        }
                    }
                    Results.Success(Unit)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun loginByGoogle(accessToken: String, state: String): Results<Unit, NetworkError> {
        return try {
            when (val result = loginApiService.loginByGoogle(accessToken, state)) {
                is ApiResults.Success -> {
                    result.data.data?.let { data ->
                        coroutineScope {
                            val loginTask = async { saveLoginData(data) }
                            val ssoTask = async { saveSsoData(data.sso) }

                            // Tunggu keduanya selesai
                            loginTask.await()
                            ssoTask.await()
                        }
                    }
                    Results.Success(Unit)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun loginByApple(accessToken: String): Results<Unit, NetworkError> {
        return try {
            when (val result = loginApiService.loginByApple(accessToken)) {
                is ApiResults.Success -> {
                    result.data.data?.let { data ->
                        coroutineScope {
                            val loginTask = async { saveLoginData(data) }
                            val ssoTask = async { saveSsoData(data.sso) }

                            // Tunggu keduanya selesai
                            loginTask.await()
                            ssoTask.await()
                        }
                    }
                    Results.Success(Unit)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun loginByPurchaseToken(): Results<Unit, NetworkError> {
        return try {
            when (val result = loginApiService.loginByPurchaseToken()) {
                is ApiResults.Success -> {
                    result.data.data?.let { data ->
                        coroutineScope {
                            val loginTask = async { saveLoginData(data) }
                            val ssoTask = async { saveSsoData(data.sso) }

                            // Tunggu keduanya selesai
                            loginTask.await()
                            ssoTask.await()
                        }
                    }
                    Results.Success(Unit)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    private suspend fun saveLoginData(data: LoginResponseData) {
        loginEmailDataSource.save(
            accessToken = data.accessToken.orEmpty(),
            refreshToken = data.refreshToken.orEmpty(),
            // nurirppan__ : disini ada isVerifyUser kalau nggak salah, kalau email bisa false atau true. kalau sosmed nggak ada apakah akan selalu true ?
            deviceKeyId = data.deviceKeyId.orEmpty(),
            isPassEmpty = data.isPassEmpty ?: false,
            isSocial = data.isSocial ?: false,
        )
    }

    private suspend fun saveSsoData(data: Sso?) {
        data?.let {
            loginEmailDataSource.save(
                sso = it
            )
        }
    }
}


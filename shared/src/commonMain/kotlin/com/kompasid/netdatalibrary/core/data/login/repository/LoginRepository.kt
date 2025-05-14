package com.kompasid.netdatalibrary.core.data.login.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.login.dataSource.LoginDataSource
import com.kompasid.netdatalibrary.core.data.login.dto.response.LoginResponseData
import com.kompasid.netdatalibrary.core.data.login.dto.response.Sso
import com.kompasid.netdatalibrary.core.data.login.network.LoginApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class LoginRepository(
    private val loginApiService: LoginApiService,
    private val loginDataSource: LoginDataSource,
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

    suspend fun loginByGoogle(accessTokenByGoogle: String, idTokenByGoogle: String): Results<Unit, NetworkError> {
        return try {
            when (val result = loginApiService.loginByGoogle(accessTokenByGoogle, idTokenByGoogle)) {
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

    suspend fun loginByApple(accessTokenByApple: String): Results<Unit, NetworkError> {
        return try {
            when (val result = loginApiService.loginByApple(accessTokenByApple)) {
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
        loginDataSource.save(
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
            loginDataSource.save(
                sso = it
            )
        }
    }
}


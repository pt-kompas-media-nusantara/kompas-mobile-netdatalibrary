package com.kompasid.netdatalibrary.core.data.loginEmail.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.dataSource.LoginEmailDataSource
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.response.LoginEmailResponseData
import com.kompasid.netdatalibrary.core.data.loginEmail.network.LoginEmailApiService
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class LoginEmailRepository(
    private val loginEmailApiService: LoginEmailApiService,
    private val loginEmailDataSource: LoginEmailDataSource,
) : ILoginEmailRepository {

    override suspend fun loginByEmail(request: LoginEmailRequest): Results<Unit, NetworkError> {
        return try {
            when (val result = loginEmailApiService.loginByEmail(request)) {
                is ApiResults.Success -> {
                    result.data.data?.let { data ->
                        saveLoginData(data)
                    }
                    Results.Success(Unit)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    private suspend fun saveLoginData(data: LoginEmailResponseData) {
        loginEmailDataSource.save(
            accessToken = data.accessToken.orEmpty(),
            refreshToken = data.refreshToken.orEmpty(),
            isVerified = data.isVerified ?: false,
            deviceKeyId = data.deviceKeyId.orEmpty(),
            isSocial = data.isSocial ?: false
        )
    }
}


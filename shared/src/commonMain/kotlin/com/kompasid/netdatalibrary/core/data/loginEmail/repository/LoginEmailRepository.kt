package com.kompasid.netdatalibrary.core.data.loginEmail.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.dataSource.LoginEmailDataSource
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.response.LoginEmailResponseData
import com.kompasid.netdatalibrary.core.data.loginEmail.network.LoginEmailApiService
import kotlinx.coroutines.coroutineScope


class LoginEmailRepository(
    private val loginEmailApiService: LoginEmailApiService,
    private val loginEmailDataSource: LoginEmailDataSource,
) : ILoginEmailRepository {

    override suspend fun postLoginEmail(request: LoginEmailRequest): Results<Unit, NetworkError> =
        coroutineScope {
            runCatching {
                loginEmailApiService.postLoginEmail(request)
            }.fold(
                onSuccess = { result ->
                    when (result) {
                        is ApiResults.Success -> {
                            result.data.data?.let { data ->
                                saveLoginData(data)
                            }
                            Results.Success(Unit)
                        }

                        is ApiResults.Error -> Results.Error(result.error)
                    }
                },
                onFailure = { Results.Error(NetworkError.Error(it)) }
            )
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




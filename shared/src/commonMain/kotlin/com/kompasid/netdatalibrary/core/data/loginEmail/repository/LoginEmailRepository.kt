package com.kompasid.netdatalibrary.core.data.loginEmail.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.models.local.LoginEmailDataSource
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.network.LoginEmailApiService



class LoginEmailRepository(
    private val loginEmailApiService: LoginEmailApiService,
    private val loginEmailDataSource: LoginEmailDataSource
) : ILoginEmailRepository {

    override suspend fun postLoginEmail(request: LoginEmailRequest): Results<Unit, NetworkError> {
        return when (val result = loginEmailApiService.postLoginEmail(request)) {
            is ApiResults.Success -> {
                result.data.run {
                    loginEmailDataSource.save(
                        accessToken ?: "",
                        refreshToken ?: "",
                        isVerified ?: false,
                        deviceKeyId ?: "",
                        isSocial ?: false
                    )
                }
                Results.Success(Unit)
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}

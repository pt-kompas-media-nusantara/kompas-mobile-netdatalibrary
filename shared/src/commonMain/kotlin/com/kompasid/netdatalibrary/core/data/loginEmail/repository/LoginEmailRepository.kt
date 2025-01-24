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
) {
    suspend fun postLoginEmail(request: LoginEmailRequest): Results<Unit, NetworkError> {
        when (val result = loginEmailApiService.postLoginEmail(request)) {
            is ApiResults.Success -> {
                result.data.let {
                    loginEmailDataSource.save(
                        it.accessToken ?: "",
                        it.refreshToken ?: "",
                        it.isVerified ?: false,
                        it.deviceKeyId ?: "",
                        it.isSocial ?: false
                    )
                }


                return Results.Success(Unit)
            }
            // Jika terjadi error
            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}


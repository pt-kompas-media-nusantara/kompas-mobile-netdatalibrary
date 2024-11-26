package com.kompasid.netdatalibrary.netData.data.loginEmailData

import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results
import com.kompasid.netdatalibrary.base.interactor.SuccessInterceptor
import com.kompasid.netdatalibrary.netData.data.loginEmailData.dto.LoginEmailRequest

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


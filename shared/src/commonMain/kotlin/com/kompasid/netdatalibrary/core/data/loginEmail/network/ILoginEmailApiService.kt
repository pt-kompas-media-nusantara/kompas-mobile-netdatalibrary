package com.kompasid.netdatalibrary.core.data.loginEmail.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.response.LoginEmailResponse

interface ILoginEmailApiService {
    suspend fun postLoginEmail(request: LoginEmailRequest): ApiResults<LoginEmailResponse, NetworkError>
}
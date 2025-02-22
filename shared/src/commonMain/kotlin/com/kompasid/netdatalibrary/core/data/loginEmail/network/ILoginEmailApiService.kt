package com.kompasid.netdatalibrary.core.data.loginEmail.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailResponse

interface ILoginEmailApiService {
    suspend fun postLoginEmail(request: LoginEmailRequest): ApiResults<LoginEmailResponse, NetworkError>
}
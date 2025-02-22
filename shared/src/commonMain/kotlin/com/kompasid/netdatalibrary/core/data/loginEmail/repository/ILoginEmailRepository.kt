package com.kompasid.netdatalibrary.core.data.loginEmail.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest

interface ILoginEmailRepository {
    suspend fun postLoginEmail(request: LoginEmailRequest): Results<Unit, NetworkError>
}
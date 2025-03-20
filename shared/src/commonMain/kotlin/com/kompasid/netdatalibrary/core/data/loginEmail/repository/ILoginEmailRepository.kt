package com.kompasid.netdatalibrary.core.data.loginEmail.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.request.LoginEmailRequest

interface ILoginEmailRepository {
    suspend fun loginByEmail(request: LoginEmailRequest): Results<Unit, NetworkError>
}
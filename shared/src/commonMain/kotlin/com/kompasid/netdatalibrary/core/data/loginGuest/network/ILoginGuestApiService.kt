package com.kompasid.netdatalibrary.core.data.loginGuest.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.loginGuest.dto.response.LoginGuestResponse

interface ILoginGuestApiService {
    suspend fun postLoginGuest(): ApiResults<LoginGuestResponse, NetworkError>
}
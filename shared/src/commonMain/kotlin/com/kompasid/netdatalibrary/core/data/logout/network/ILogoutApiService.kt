package com.kompasid.netdatalibrary.core.data.logout.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError

interface ILogoutApiService {
    suspend fun postLogout(): ApiResults<Unit, NetworkError>
}
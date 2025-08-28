package com.kompasid.netdatalibrary.core.data.logout.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results

interface ILogoutRepository {
    suspend fun postLogout(): Results<Unit, NetworkError>
}
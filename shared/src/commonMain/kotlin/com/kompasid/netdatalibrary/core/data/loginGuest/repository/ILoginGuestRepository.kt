package com.kompasid.netdatalibrary.core.data.loginGuest.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results

interface ILoginGuestRepository {
    suspend fun postLoginGuest(): Results<String, NetworkError>
}
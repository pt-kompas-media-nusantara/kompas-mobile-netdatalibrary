package com.kompasid.netdatalibrary.base.network.NetworkVM

import com.kompasid.netdatalibrary.base.network.NetworkError

interface INetworkVM {
    suspend fun statusToError(error: NetworkError)
}

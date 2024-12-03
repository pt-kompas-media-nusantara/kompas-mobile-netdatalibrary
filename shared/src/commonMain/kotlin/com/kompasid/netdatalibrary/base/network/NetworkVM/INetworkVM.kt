package com.kompasid.netdatalibrary.base.network.NetworkVM

import com.kompasid.netdatalibrary.base.network.NetworkError

interface INetworkVM {
    fun statusToError(error: NetworkError)
}

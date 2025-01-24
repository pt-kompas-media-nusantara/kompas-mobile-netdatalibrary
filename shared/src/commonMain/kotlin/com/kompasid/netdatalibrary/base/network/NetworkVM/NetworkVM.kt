package com.kompasid.netdatalibrary.base.network.NetworkVM

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.NetworkError


class NetworkVM : INetworkVM {
    override fun statusToError(error: NetworkError) {
        when (error) {
            // error mau di tampilin view apa saja
            is NetworkError.Error -> Logger.debug { "Error: ${error.throwable.message}" }
            is NetworkError.NoInternet -> Logger.debug { "Error: ${error.toString()}" }
            is NetworkError.NotFound -> Logger.debug { "Error: ${error.toString()}" }
            is NetworkError.RequestTimeout -> Logger.debug { "Error: ${error.toString()}" }
            is NetworkError.ServerError -> Logger.debug { "Error: ${error.toString()}" }
            is NetworkError.Technical -> Logger.debug { "Error: ${error.toString()}" }
            is NetworkError.Unauthorized -> Logger.debug { "Error: ${error.toString()}" }
            NetworkError.Unknown -> Logger.debug { "Unknown Error" }
        }
    }
}
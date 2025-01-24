package com.kompasid.netdatalibrary.core.data.generalContent.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.GeneralContentResponse

interface GeneralContentApiService {
    suspend fun getGeneralContent(): ApiResults<GeneralContentResponse, NetworkError>
}
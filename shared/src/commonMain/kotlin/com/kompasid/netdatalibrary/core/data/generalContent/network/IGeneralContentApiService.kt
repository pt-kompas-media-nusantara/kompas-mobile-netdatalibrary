package com.kompasid.netdatalibrary.core.data.generalContent.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.GeneralContentResponse

interface IGeneralContentApiService {
    suspend fun getGeneralContent(customUrl: String = ApiConfig.GENERAL_URL): ApiResults<GeneralContentResponse, NetworkError>
}
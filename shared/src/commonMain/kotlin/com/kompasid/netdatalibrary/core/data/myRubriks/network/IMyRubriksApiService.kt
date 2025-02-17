package com.kompasid.netdatalibrary.core.data.myRubriks.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.OldMyRubriksResponse

interface IMyRubriksApiService {
    suspend fun getMyRubriks(): ApiResults<OldMyRubriksResponse, NetworkError>
}
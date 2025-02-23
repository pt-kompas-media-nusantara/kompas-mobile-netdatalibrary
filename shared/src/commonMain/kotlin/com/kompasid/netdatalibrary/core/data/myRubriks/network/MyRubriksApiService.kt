package com.kompasid.netdatalibrary.core.data.myRubriks.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.OldMyRubriksResponse
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.SaveMyRubrikResponse
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class MyRubriksApiService(
    private val httpClient: HttpClient,
    private val settingsHelper: SettingsHelper
) : IMyRubriksApiService {
    override suspend fun getRubrikList(): ApiResults<OldMyRubriksResponse, NetworkError> {
        return safeCall<OldMyRubriksResponse> {
            httpClient.get(ApiConfig.MY_RUBRIKS_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                bearerAuth(
                    ""
                )
            }
        }
    }

    suspend fun saveMyRubriks(request: SaveMyRubrikRequest): ApiResults<SaveMyRubrikResponse, NetworkError> {
        return safeCall<SaveMyRubrikResponse> {
            httpClient.post(ApiConfig.MY_RUBRIKS_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
                bearerAuth(
                    ""
                )
            }
        }
    }
}




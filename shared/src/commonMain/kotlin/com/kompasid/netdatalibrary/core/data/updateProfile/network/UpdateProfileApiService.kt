package com.kompasid.netdatalibrary.core.data.updateProfile.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileRequest
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.response.UpdateProfileResponse
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class UpdateProfileApiService(
    private val httpClient: HttpClient,
    private val tokenInterceptor: TokenInterceptor,
) : IUpdateProfileApiService {

    override suspend fun updateProfile(request: UpdateProfileRequest): ApiResults<UpdateProfileResponse, NetworkError> {
        return tokenInterceptor.withValidToken { validToken ->
            safeCall<UpdateProfileResponse> {
                httpClient.put(ApiConfig.UPDATE_PROFILE_URL) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }
        }
    }
}



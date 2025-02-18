package com.kompasid.netdatalibrary.core.data.loginGuest.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.core.data.loginGuest.dto.request.LoginGuestRequest
import com.kompasid.netdatalibrary.core.data.loginGuest.dto.response.LoginGuestResponse
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class LoginGuestApiService(
    private val httpClient: HttpClient
) : ILoginGuestApiService {
    override suspend fun postLoginGuest(): ApiResults<LoginGuestResponse, NetworkError> {
        return safeCall<LoginGuestResponse> {
            httpClient.post(ApiConfig.LOGIN_GUEST_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(LoginGuestRequest("anonynous.user@kompas.id"))
            }
        }
    }
}
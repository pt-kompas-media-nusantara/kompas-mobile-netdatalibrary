package com.kompasid.netdatalibrary.core.data.userDetail.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.userDetail.dto.response.OldUserDetailResponse
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserDetailApiService(
    private val httpClient: HttpClient,
    private val settingsUseCase: SettingsUseCase
) : IUserDetailApiService {
    override suspend fun getUserDetail(): ApiResults<OldUserDetailResponse, NetworkError> {
        return safeCall<OldUserDetailResponse> {
            httpClient.get(ApiConfig.USER_DETAIL_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                bearerAuth(settingsUseCase.getString(KeySettingsType.ACCESS_TOKEN))
            }
        }
    }
}

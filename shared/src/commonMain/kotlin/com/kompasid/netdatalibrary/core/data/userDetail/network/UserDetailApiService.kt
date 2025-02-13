package com.kompasid.netdatalibrary.core.data.userDetail.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.core.data.userDetailData.dto.OldUserDetailResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserDetailApiService(
    private val httpClient: HttpClient,
    private val settingsDataSource: SettingsDataSource
) : IUserDetailApiService {
    override suspend fun getUserDetailOld(): ApiResults<OldUserDetailResponse, NetworkError> {
        return safeCall<OldUserDetailResponse> {
            httpClient.get(ApiConfig.USER_DETAIL_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                bearerAuth(settingsDataSource.getStringFlow(KeySettingsType.ACCESS_TOKEN).value ?: "")
            }
        }
    }
}

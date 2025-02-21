package com.kompasid.netdatalibrary.core.data.userHistoryMembership.network


import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto.OldUserHistoryMembershipResponse
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType


class UserHistoryMembershipApiService(
    private val httpClient: HttpClient,
    private val settingsUseCase: SettingsUseCase
) : IUserHistoryMembershipApiService {
    override suspend fun getUserHistoryMembership(): ApiResults<OldUserHistoryMembershipResponse, NetworkError> {
        return safeCall<OldUserHistoryMembershipResponse> {
            httpClient.get(ApiConfig.USER_HISTORY_MEMBERSHIP_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                bearerAuth(settingsUseCase.getString(KeySettingsType.ACCESS_TOKEN))
            }
        }
    }
}

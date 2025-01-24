package com.kompasid.netdatalibrary.core.data.logout.repository

import com.kompasid.netdatalibrary.core.data.logout.model.dto.LogoutRequest
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.core.data.logout.network.LogoutApiService
import com.kompasid.netdatalibrary.core.data.logout.model.local.LogoutDataSource


class LogoutRepository(
    private val logoutApiService: LogoutApiService,
    private val logoutDatasource: LogoutDataSource,
    private val settingsDataSource: SettingsDataSource,
) {
    suspend fun postLogout(): Results<Unit, NetworkError> {
        val refreshToken = settingsDataSource.load(KeySettingsType.REFRESH_TOKEN, "")
        when (val result = logoutApiService.postLogout(LogoutRequest(refreshToken))) {
            is ApiResults.Success -> {
                logoutDatasource.logout()
                return Results.Success(Unit)
            }

            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }

    suspend fun removeDatasource() {
        logoutDatasource.logout()
    }
}
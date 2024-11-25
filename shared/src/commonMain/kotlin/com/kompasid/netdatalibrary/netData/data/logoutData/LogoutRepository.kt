package com.kompasid.netdatalibrary.netData.data.logoutData

import com.kompasid.app.netdatamodule.Example.Data.LogoutData.DTO.ApiService.LogoutRequest
import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results
import com.kompasid.netdatalibrary.base.interactor.SuccessInterceptor
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource


class LogoutRepository(
    private val logoutApiService: LogoutApiService,
    private val logoutDatasource: LogoutDatasource,
    private val settingsDataSource: SettingsDataSource,
) {
    suspend fun postLogout(): Results<SuccessInterceptor, NetworkError> {
        val refreshToken = settingsDataSource.load(KeySettingsType.REFRESH_TOKEN, "")
        when (val result = logoutApiService.postLogout(LogoutRequest(refreshToken))) {
            is ApiResults.Success -> {
                logoutDatasource.logout()
                return Results.Success(SuccessInterceptor())
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
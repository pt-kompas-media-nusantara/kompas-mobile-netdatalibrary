package com.kompasid.netdatalibrary.netData.data.appIconData

import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results
import com.kompasid.netdatalibrary.netData.data.appIconData.dto.AppIconResponse
import com.kompasid.netdatalibrary.netData.data.appIconData.AppIconApiService

class AppIconRepository(
    private val appIconApiService: AppIconApiService
) {
    suspend fun getAppIcon(): Results<AppIconResponse, NetworkError> {
        when (val result = appIconApiService.getAppIcon()) {
            is ApiResults.Success -> {

                val response = result.data
                // nurirppan : save app icon ke database local. dan berikan icon default

                return Results.Success(response)
            }

            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}
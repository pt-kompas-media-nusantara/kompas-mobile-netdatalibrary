package com.kompasid.netdatalibrary.netData.data.appIconData

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.netData.data.appIconData.dto.AppIconResponse

class AppIconRepository(
    private val appIconApiService: AppIconApiService
) {

    suspend fun getAppIcon(): Results<AppIconResponse, NetworkError> = when (val result = appIconApiService.getAppIcon()) {
        is ApiResults.Success -> {
            val response = result.data
            // Nurirppan: Simpan app icon ke database lokal dan gunakan icon default jika perlu.
            Results.Success(response)
        }
        is ApiResults.Error -> {
            Results.Error(result.error)
        }
    }

    suspend fun createDataToDb() {

    }

    suspend fun readDataToDb() {

    }

    suspend fun updateDataToDb() {

    }

    suspend fun deleteDataToDb() {

    }

}
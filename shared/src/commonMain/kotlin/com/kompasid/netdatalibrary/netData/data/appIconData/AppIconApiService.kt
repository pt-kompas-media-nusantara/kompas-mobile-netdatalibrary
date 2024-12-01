package com.kompasid.netdatalibrary.netData.data.appIconData

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.netData.data.appIconData.dto.AppIconResponse
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.INetworkApiService
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.utilities.Constants


//https://cdn-content.kompas.id/mobile/json/generalContent.json   | app icon
//https://private-d6360b-hariankompasios.apiary-mock.com/generalContent
class AppIconApiService(
    private val iNetworkApiService: INetworkApiService
) {

    suspend fun getAppIcon(): ApiResults<AppIconResponse, NetworkError> = try {
        val (body, code) = iNetworkApiService.fetchDataFromApi(ApiConfig.APP_ICON_URL)
        val response: AppIconResponse? = body as? AppIconResponse

        if (response == null) {
            ApiResults.Error(NetworkError.Technical(code.value, code.description))
        } else if (Constants.isUsinStatusCode) {
            handleCustomStatusCode(response)
        } else {
            handleHttpStatusCode(code.value, code.description, response)
        }
    } catch (e: Exception) {
        ApiResults.Error(iNetworkApiService.mapExceptionToError(e))
    }

    private fun handleCustomStatusCode(response: AppIconResponse): ApiResults<AppIconResponse, NetworkError> {
        return when (val responseCode = response.code) {
            200 -> ApiResults.Success(response)
            400 -> ApiResults.Error(
                NetworkError.Technical(
                    responseCode,
                    response.message ?: "Bad request"
                )
            )

            else -> ApiResults.Error(iNetworkApiService.mapHttpStatusToError(responseCode ?: 500))
        }
    }

    private fun handleHttpStatusCode(
        code: Int,
        description: String,
        response: AppIconResponse
    ): ApiResults<AppIconResponse, NetworkError> {
        return when (val statusCode = code) {
            200 -> ApiResults.Success(response)
            400 -> ApiResults.Error(NetworkError.Technical(statusCode, description))
            else -> ApiResults.Error(iNetworkApiService.mapHttpStatusToError(statusCode))
        }
    }
}

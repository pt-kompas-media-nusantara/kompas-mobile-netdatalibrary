package com.kompasid.netdatalibrary.netData.data.userDetailData

import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.base.response.BaseResponse
import com.kompasid.netdatalibrary.netData.data.userDetailData.dto.UserDetailResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.io.IOException


class UserDetailApiService(
    private val httpClient: HttpClient,
    private val settingsDataSource: SettingsDataSource
) {

    suspend fun getUserDetail(): ApiResults<UserDetailResponse, NetworkError> {
        try {
            // Melakukan request ke API dan menangkap HttpResponse
            val httpResponse: HttpResponse =
                httpClient.get("https://api.kompas.id/account/api/v2/users/detail") {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(settingsDataSource.getStringFlow(KeySettingsType.ACCESS_TOKEN).value ?: "")
                }

            val statusCode = httpResponse.status.value

            val response: BaseResponse<UserDetailResponse> = httpResponse.body()

            when (response.code) {
                200 -> {
                    response.data?.let {
                        return ApiResults.Success(it)
                    } ?: return ApiResults.Error(NetworkError.Technical(response.code ?: 500, response.message ?: ""))
                }

                400 -> return ApiResults.Error(NetworkError.Technical(response.code ?: 500, response.message ?: ""))
                401 -> return ApiResults.Error(NetworkError.Unauthorized)
                404 -> return ApiResults.Error(NetworkError.NotFound)
                in 500..599 -> return ApiResults.Error(NetworkError.ServerError)
                else -> return ApiResults.Error(NetworkError.Technical(response.code ?: 500, response.message ?: ""))
            }

        } catch (e: RedirectResponseException) {
            // 3xx responses (redirects)
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: ClientRequestException) {
            // 4xx responses (client errors)
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: ServerResponseException) {
            // 5xx responses (server errors)
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: IOException) {
            // Jaringan tidak tersedia atau permintaan mengalami timeout
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: Exception) {
            // Error lain, misalnya masalah serialisasi
            return ApiResults.Error(NetworkError.Error(e))
        }
    }
}
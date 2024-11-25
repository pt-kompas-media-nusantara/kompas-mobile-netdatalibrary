package com.kompasid.netdatalibrary.netData.data.refreshTokenData

import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.base.response.BaseResponse
import com.kompasid.netdatalibrary.netData.data.refreshTokenData.dto.RefreshTokenRequest
import com.kompasid.netdatalibrary.netData.data.refreshTokenData.dto.RefreshTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.io.IOException


class RefreshTokenApiService(
    private val httpClient: HttpClient,
    private val settingsDataSource: SettingsDataSource
) {

    suspend fun postRefreshToken(): ApiResults<RefreshTokenResponse, NetworkError> {
        val request = RefreshTokenRequest(
            settingsDataSource.getStringFlow(KeySettingsType.REFRESH_TOKEN).value ?: ""
        )
        try {
            val httpResponse: HttpResponse =
                httpClient.post("https://api.kompas.id/account/api/v1/tokens/refresh") {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(request)
                }

            val statusCode = httpResponse.status.value

            val response: BaseResponse<RefreshTokenResponse> = httpResponse.body()

            when (response.code) {
                in 200..299 -> {
                    response.data?.let {
                        return ApiResults.Success(it)
                    } ?: return ApiResults.Error(
                        NetworkError.Technical(
                            response.code ?: 500,
                            response.message ?: ""
                        )
                    )
                }

                400 -> return ApiResults.Error(
                    NetworkError.Technical(
                        response.code, response.message ?: ""
                        ?: ""
                    )
                )

                401 -> return ApiResults.Error(NetworkError.Unauthorized)
                404 -> return ApiResults.Error(NetworkError.NotFound)
                in 500..599 -> return ApiResults.Error(NetworkError.ServerError)
                else -> return ApiResults.Error(
                    NetworkError.Technical(
                        response.code ?: 500,
                        "error"
                    )
                )
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
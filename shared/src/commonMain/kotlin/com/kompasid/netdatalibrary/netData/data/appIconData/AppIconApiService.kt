package com.kompasid.netdatalibrary.netData.data.appIconData

import com.kompasid.netdatalibrary.netData.data.appIconData.dto.AppIconResponse
import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType


//https://cdn-content.kompas.id/mobile/json/generalContent.json   | app icon
//https://private-d6360b-hariankompasios.apiary-mock.com/generalContent
class AppIconApiService(
    private val httpClient: HttpClient
) {

    suspend fun getAppIcon(): ApiResults<AppIconResponse, NetworkError> {
        try {

            val httpResponse: HttpResponse =
                httpClient.get("https://cdn-content.kompas.id/mobile/json/generalContent.json") {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }


            val response: AppIconResponse = httpResponse.body()
            val statusCode = httpResponse.status.value

            when (statusCode) {
                200 -> {
                    return ApiResults.Success(response)
                }

                400 -> return ApiResults.Error(
                    NetworkError.Technical(
                        400,
                        "error"
                    )
                )

                401 -> return ApiResults.Error(NetworkError.Unauthorized)
                404 -> return ApiResults.Error(NetworkError.NotFound)
                in 500..599 -> return ApiResults.Error(NetworkError.ServerError)
                else -> return ApiResults.Error(
                    NetworkError.Technical(
                        statusCode,
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
        } catch (e: kotlinx.io.IOException) {
            // Jaringan tidak tersedia atau permintaan mengalami timeout
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: Exception) {
            // Error lain, misalnya masalah serialisasi
            return ApiResults.Error(NetworkError.Error(e))
        }
    }
}
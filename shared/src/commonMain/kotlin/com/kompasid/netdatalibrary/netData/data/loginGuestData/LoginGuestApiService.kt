package com.kompasid.netdatalibrary.netData.data.loginGuestData

import com.kompasid.netdatalibrary.netData.data.loginGuestData.dto.LoginGuestRequest
import com.kompasid.netdatalibrary.netData.data.loginGuestData.dto.LoginGuestResponse
import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.response.BaseResponse
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


class LoginGuestApiService(
    private val httpClient: HttpClient
) {

    // Mengembalikan ApiResult dengan hanya LoginGuestResponse
    suspend fun postLoginGuest(): ApiResults<LoginGuestResponse, NetworkError> {
        try {
            // Melakukan request ke API dan menangkap HttpResponse
            val httpResponse: HttpResponse =
                httpClient.post("https://api.kompas.id/account/api/v2/login/guest") {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(LoginGuestRequest("anonynous.user@kompas.id"))
                }

            // val statusCode = httpResponse.status.value

            // Parsing body menjadi BaseAuthResponse
            val response: BaseResponse<LoginGuestResponse> = httpResponse.body()

            when (response.code) {
                200 -> {
                    response.data?.let {
                        return ApiResults.Success(it)
                    } ?: return ApiResults.Error(
                        NetworkError.Technical(
                            response.code,
                            response.message ?: ""
                        )
                    )
                }

                400 -> return ApiResults.Error(
                    NetworkError.Technical(
                        response.code,
                        response.message ?: ""
                    )
                )

                401 -> return ApiResults.Error(NetworkError.Unauthorized)
                404 -> return ApiResults.Error(NetworkError.NotFound)
                in 500..599 -> return ApiResults.Error(NetworkError.ServerError)
                else -> return ApiResults.Error(
                    NetworkError.Technical(
                        response.code ?: 500,
                        response.message ?: ""
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
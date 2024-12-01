package com.kompasid.netdatalibrary.netData.data.articleListData

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.response.BaseArticlesResponse
import com.kompasid.netdatalibrary.netData.data.articleListData.dto.ArticleListResponse
import com.kompasid.netdatalibrary.netData.domain.tokenDomain.TokenInterceptor
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


class ArticleListApiService(
    private val httpClient: HttpClient,
    private val tokenInterceptor: TokenInterceptor,
) {

    suspend fun getArticleList(
        type: ArticleListType
    ): ApiResults<List<ArticleListResponse>, NetworkError> {
        return tokenInterceptor.withValidToken { validToken ->
            try {
                val httpResponse: HttpResponse =
                    httpClient.get(type.fullUrl()) {
                        contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                        bearerAuth(validToken)
                    }

                val response: BaseArticlesResponse<ArticleListResponse> = httpResponse.body()

                when (response.code) {
                    in 200..299 -> {
                        response.result?.filterNotNull()?.let { nonNullResults ->
                            ApiResults.Success(nonNullResults)
                        } ?: ApiResults.Error(
                            NetworkError.Technical(
                                response.code ?: 500,
                                response.message?.message ?: ""
                            )
                        )
                    }

                    400 -> ApiResults.Error(
                        NetworkError.Technical(
                            response.code,
                            response.message?.message ?: ""
                        )
                    )

                    401 -> ApiResults.Error(NetworkError.Unauthorized)
                    404 -> ApiResults.Error(NetworkError.NotFound)
                    in 500..599 -> ApiResults.Error(NetworkError.ServerError)
                    else -> ApiResults.Error(
                        NetworkError.Technical(
                            response.code ?: 500,
                            "Unknown error"
                        )
                    )
                }
            } catch (e: RedirectResponseException) {
                ApiResults.Error(NetworkError.Error(e))
            } catch (e: ClientRequestException) {
                ApiResults.Error(NetworkError.Error(e))
            } catch (e: ServerResponseException) {
                ApiResults.Error(NetworkError.Error(e))
            } catch (e: IOException) {
                ApiResults.Error(NetworkError.Error(e))
            } catch (e: Exception) {
                ApiResults.Error(NetworkError.Error(e))
            }
        }
    }
}
/*
class ArticleListApiService(
    private val httpClient: HttpClient,
    private val settingsDataSource: SettingsDataSource,
) {

    suspend fun getArticleList(
        type: ArticleListType
    ): ApiResult<List<ArticleListResponse>, NetworkError> {
        try {

            val httpResponse: HttpResponse =
                httpClient.get(type.fullUrl()) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(settingsDataSource.getStringFlow(KeySettingsType.ACCESS_TOKEN).value ?: "")
                }


            val response: BaseArticlesResponse<ArticleListResponse> = httpResponse.body()

            when (response.code) {
                200 -> {
                    val nonNullResults = response.result.filterNotNull()
                    return ApiResult.Success(nonNullResults)
                }

                400 -> return ApiResult.Error(
                    NetworkError.Technical(
                        response.code,
                        response.message.message
                    )
                )

                401 -> return ApiResult.Error(NetworkError.Unauthorized)
                404 -> return ApiResult.Error(NetworkError.NotFound)
                in 500..599 -> return ApiResult.Error(NetworkError.ServerError)
                else -> return ApiResult.Error(
                    NetworkError.Technical(
                        response.code,
                        response.message.message
                    )
                )
            }

        } catch (e: RedirectResponseException) {
            // 3xx responses (redirects)
            return ApiResult.Error(NetworkError.Error(e))
        } catch (e: ClientRequestException) {
            // 4xx responses (client errors)
            return ApiResult.Error(NetworkError.Error(e))
        } catch (e: ServerResponseException) {
            // 5xx responses (server errors)
            return ApiResult.Error(NetworkError.Error(e))
        } catch (e: kotlinx.io.IOException) {
            // Jaringan tidak tersedia atau permintaan mengalami timeout
            return ApiResult.Error(NetworkError.Error(e))
        } catch (e: Exception) {
            // Error lain, misalnya masalah serialisasi
            return ApiResult.Error(NetworkError.Error(e))
        }
    }
}
*/

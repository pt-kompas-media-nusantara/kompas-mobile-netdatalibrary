package com.kompasid.netdatalibrary.base.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val networkModule = module {

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                val timeout = 15000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(message = message, tag = "HTTPS Client")
                    }
                }
                level = LogLevel.ALL
            }
        }
            .also { Napier.base(DebugAntilog()) }
    }
}

/**
 * The OAuth authorization flow for our application looks as follows:
 *
 * (1)  --> Authorization request                Resource owner
 * (2)  <-- Authorization grant (code)           Resource owner
 * (3)  --> Authorization grant (code)           Authorization server
 * (4)  <-- Access and refresh tokens            Authorization server
 * (5)  --> Request with valid token             Resource server
 * (6)  <-- Protected resource                   Resource server
 * ⌛⌛⌛    Token expired
 * (7)  --> Request with expired token           Resource server
 * (8)  <-- 401 Unauthorized response            Resource server
 * (9)  --> Authorization grant (refresh token)  Authorization server
 * (10) <-- Access and refresh tokens            Authorization server
 * (11) --> Request with new token               Resource server
 * (12) <-- Protected resource                   Resource server
 * */

//catatan :
// ada error nggak jelas pakai bearer access token dan refresh token. hapus dulu
//https://medium.com/@lahirujay/token-refresh-implementation-with-ktor-in-kotlin-multiplatform-mobile-f4d77b33b355
//https://ktor.io/docs/client-bearer-auth.html#step11

/*
val networkModule = module {
    single<SettingsDataSource> { SettingsDataSource() }

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val settingsDataSource = get<SettingsDataSource>()
                        val accessToken =
                            settingsDataSource.getStringFlow(KeySettingsType.ACCESS_TOKEN)
                                .firstOrNull()
                        val refreshToken =
                            settingsDataSource.getStringFlow(KeySettingsType.REFRESH_TOKEN)
                                .firstOrNull()

                        if (accessToken != null && refreshToken != null) {
                            BearerTokens(accessToken, refreshToken)
                        } else {
                            null // Jika token tidak tersedia
                        }
                    }

                    // Fungsi untuk refresh token ketika access token kedaluwarsa
//                    refreshTokens {
//                        val settingsDataSource = get<SettingsDataSource>()
//                        val refreshToken =
//                            settingsDataSource.getStringFlow(KeySettingsType.REFRESH_TOKEN)
//                                .firstOrNull()
//
//                        if (refreshToken != null) {
//                            // Mengambil token baru menggunakan refresh token
//                            val tokenResponse: RefreshTokenResponse = client.submitForm(
//                                url = "https://api.kompas.id/account/api/v1/tokens/refresh",
//                                formParameters = parameters {
//                                    append("grant_type", "refresh_token")
//                                    append("refresh_token", refreshToken)
//                                }
//                            ).body()
//
//                            // Menyimpan access token dan refresh token yang baru ke SettingsDataSource
//                            settingsDataSource.save(
//                                KeySettingsType.ACCESS_TOKEN,
//                                tokenResponse.data.accessToken
//                            )
//                            settingsDataSource.save(
//                                KeySettingsType.REFRESH_TOKEN,
//                                tokenResponse.data.refreshToken
//                            )
//
//                            // Mengembalikan token yang baru
//                            BearerTokens(tokenResponse.data.accessToken, tokenResponse.data.refreshToken)
//                        } else {
//                            null // Jika refresh token tidak tersedia
//                        }
//                    }
                }
            }

            install(HttpTimeout) {
                val timeout = 15000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(message = message, tag = "HTTPS Client")
                    }
                }
                level = LogLevel.ALL
            }
        }
            .also { Napier.base(DebugAntilog()) }
    }
}
*/

//install(Auth) {
//    bearer {
//        refreshTokens {
//            val userManager: UserManager = get() // Secure Token Store
//
//            // Membuat request POST dengan body JSON
//            val refreshTokenInfo: TokenInfo =
//                client.post("https://api.kompas.id/account/api/v1/tokens/refresh") {
//                    contentType(ContentType.Application.Json)
//                    accept(ContentType.Application.Json)
//                    setBody(
//                        mapOf(
////                                        "refreshToken" to (userManager.get().refreshToken ?: "")
//                            "refreshToken" to ("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im51ci5pcmZhbkBrb21wYXMuY29tIiwiZXhwIjoxNzYzMTA5NTMxLCJpZCI6Ijk0N2JhZjk2LTY3Y2QtNDVhNy1hYzMwLWU5MDA2NTFhOTZjMCIsInN1YiI6MX0.GVmDbL-T2YGWsK9pmX3Rsr8T-dX2JtbgEXYZlqIB8ePslX5Es-_tkANn93XmeydZJvzNlRQp2L4Q5Z39WSD4RA5HvTV9Kzmn0VKgcdwwDYT1gy-KDPOGqAWe1qqdUx8wR5NDXpM_p8cmY8Yb6QYYL9g4CWF_NHl681roqPNhyME")
//                        )
//                    )
//                    markAsRefreshTokenRequest() // Opsi tambahan jika diperlukan
//                }.body()
//
//            // Menyimpan token yang baru di UserManager
//            userManager.connect(
//                SessionData(
//                    accessToken = refreshTokenInfo.accessToken,
//                    idToken = refreshTokenInfo.idToken,
//                    refreshToken = refreshTokenInfo.refreshToken
//                )
//            )
//
//            // Membuat BearerTokens untuk otentikasi
//            val accessToken = refreshTokenInfo.accessToken
//            val refreshToken = userManager.get().refreshToken
//
//            println(accessToken)
//            println(refreshToken)
//            BearerTokens(accessToken, refreshToken)
//        }
//    }
//}
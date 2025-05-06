package com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiEnv.FlavorsType
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.request.PurchaseTokenCheckRequest
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.response.PurchaseTokenCheckResponse
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import io.ktor.client.request.post
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PurchaseTokenCheckApiService(
    private val httpClient: HttpClient,
    private val apiEnvironment: ApiEnvironment,
    private val settingsHelper: SettingsHelper
) : IPurchaseTokenCheckApiService {

    suspend fun purchaseTokenCheck(datetime: String, signature: String): ApiResults<PurchaseTokenCheckResponse, NetworkError> {
        val url = apiEnvironment.getPurchaseTokenCheckUrl()

        val token: List<String> = settingsHelper.get(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList())
        val membershipChannelId: Int = when (apiEnvironment.flavors()) {
            FlavorsType.RELEASE_KMP -> 5
            else -> 5 // 5 : PROD | 13 : DEV
        }
        val request = PurchaseTokenCheckRequest(
            membershipChannelId = membershipChannelId,
            token = token.last(),
        )

        return safeCall<PurchaseTokenCheckResponse> {
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
                header("datetime", datetime)
                header("x-signature", signature)
            }
        }
    }
}

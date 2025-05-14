package com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class PurchaseTokenCheckResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: PurchaseTokenCheckData? = null,
    @SerialName("errors")
    var errors: String? = null,
    @SerialName("message")
    var message: String? = null
)

@Serializable
data class PurchaseTokenCheckData(
    @SerialName("email")
    var email: String? = null,
    @SerialName("registerBy")
    var registerBy: String? = null
)


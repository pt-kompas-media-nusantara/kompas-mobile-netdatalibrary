package com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.request
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class PurchaseTokenCheckRequest(
    @SerialName("membershipChannelId")
    var membershipChannelId: Int,
    @SerialName("token")
    var token: String
)


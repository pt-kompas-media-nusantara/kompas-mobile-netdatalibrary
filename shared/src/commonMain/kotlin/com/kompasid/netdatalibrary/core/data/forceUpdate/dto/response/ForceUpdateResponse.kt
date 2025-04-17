package com.kompasid.netdatalibrary.core.data.forceUpdate.dto.response
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
@Serializable


data class ForceUpdateResponse(
    @SerialName("maxVersion")
    var maxVersion: String? = null,
    @SerialName("minVersion")
    var minVersion: String? = null,
    @SerialName("mobileVersion")
    var mobileVersion: String? = null
)




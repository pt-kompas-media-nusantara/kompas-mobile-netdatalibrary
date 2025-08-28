package com.kompasid.netdatalibrary.core.data.updateProfile.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UpdateProfileRequest(
    @SerialName("city")
    var city: String? = null,
    @SerialName("country")
    var country: String? = null,
    @SerialName("dateBirth")
    var dateBirth: String? = null,
    @SerialName("firstName")
    var firstName: String? = null,
    @SerialName("gender")
    var gender: Int? = null,
    @SerialName("lastName")
    var lastName: String? = null,
    @SerialName("phoneNumber")
    var phoneNumber: String? = null,
    @SerialName("province")
    var province: String? = null,
    @SerialName("userName")
    var userName: String? = null
)

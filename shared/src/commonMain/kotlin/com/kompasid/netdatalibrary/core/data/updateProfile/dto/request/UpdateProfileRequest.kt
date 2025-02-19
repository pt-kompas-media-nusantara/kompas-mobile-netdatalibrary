package com.kompasid.netdatalibrary.core.data.updateProfile.dto.request

data class UpdateProfileRequest(
    val city: String,
    val country: String,
    val dateBirth: String,
    val firstName: String,
    val gender: Int,
    val lastName: String,
    val phoneNumber: String,
    val province: String,
    val userName: String
)
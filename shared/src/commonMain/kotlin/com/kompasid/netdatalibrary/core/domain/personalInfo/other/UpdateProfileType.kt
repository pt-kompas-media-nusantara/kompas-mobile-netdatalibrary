package com.kompasid.netdatalibrary.core.domain.personalInfo.other

sealed class UpdateProfileType {
    data class Fullname(val firstName: String, val lastName: String) : UpdateProfileType()
    data class Gender(val gender: Int) : UpdateProfileType()
    data class DateBirth(val dateBirth: String) : UpdateProfileType()
    data class Domicile(val country: String, val province: String, val city: String) : UpdateProfileType()
    data class PhoneNumber(val phoneNumber: String) : UpdateProfileType()
}
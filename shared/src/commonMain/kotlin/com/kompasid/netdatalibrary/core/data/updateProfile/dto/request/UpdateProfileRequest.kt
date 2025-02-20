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


sealed class UpdateProfileType {
    data class Fullname(val firstName: String, val lastName: String) : UpdateProfileType()
    data class Gender(val gender: Int) : UpdateProfileType()
    data class DateBirth(val dateBirth: String) : UpdateProfileType()
    data class Domicile(val country: String, val province: String, val city: String) : UpdateProfileType()
    data class Email(val email: String) : UpdateProfileType()
    data class PhoneNumber(val phoneNumber: String) : UpdateProfileType()
}

//fun updateProfile(updateType: UpdateProfileType) {
//    when (updateType) {
//        is UpdateProfileType.Fullname -> println("Fullname: ${updateType.firstName} ${updateType.lastName}")
//        is UpdateProfileType.Gender -> println("Gender: ${updateType.gender}")
//        is UpdateProfileType.DateBirth -> println("Date of Birth: ${updateType.dateBirth}")
//        is UpdateProfileType.Domicile -> println("Domicile: ${updateType.city}, ${updateType.province}, ${updateType.country}")
//        is UpdateProfileType.Email -> println("Email: ${updateType.email}")
//        is UpdateProfileType.PhoneNumber -> println("Phone Number: ${updateType.phoneNumber}")
//    }
//}

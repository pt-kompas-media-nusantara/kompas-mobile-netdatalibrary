package com.kompasid.netdatalibrary.helpers

object TextValidator {

    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private val phoneRegex = Regex("^\\+?[0-9]{9,15}\$")

    fun detectType(input: String): TextType {
        val trimmedInput = input.trim()
        return when {
            emailRegex.matches(trimmedInput) -> TextType.Email
            phoneRegex.matches(trimmedInput) -> TextType.PhoneNumber
            else -> TextType.Email
        }
    }
}


enum class TextType(val value: String) {
    Email("email"),
    PhoneNumber("phoneNumber"),
}


/**
emailRegex:
Valid untuk email umum seperti nama@mail.com

phoneRegex:
Minimal 9 digit dan maksimal 15 digit
Mendukung + di awal (untuk format internasional)
Cocok untuk nomor Indonesia, misal 08123456789 atau +628123456789
 */
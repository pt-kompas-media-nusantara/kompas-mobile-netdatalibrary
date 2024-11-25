package com.kompasid.netdatalibrary.base.validation

import io.konform.validation.Validation

// dibuat 1 1 aja, dibikin array jadi lebih mudah penggunaannya
object ValidationRules {

    val emptyValidation = Validation<String> {
        // Validasi agar tidak kosong setelah di-trim
        addConstraint("Input must not be empty") { it.trim().isNotEmpty() }
    }

    val emailValidation = Validation<String> {
        // Validasi agar tidak kosong setelah di-trim
        addConstraint("Email must not be empty") { it.trim().isNotEmpty() }
        // Validasi format email setelah di-trim
        addConstraint("Invalid email format") {
            it.trim().matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))
        }
    }

    val passwordValidation = Validation<String> {
        // Validasi agar tidak kosong setelah di-trim
        addConstraint("Password must not be empty") { it.trim().isNotEmpty() }
        // Validasi panjang minimal 8 karakter setelah di-trim
        addConstraint("Password must be at least 8 characters long") { it.trim().length >= 8 }
        // Validasi adanya huruf besar setelah di-trim
        addConstraint("Password must contain at least one uppercase letter") {
            it.trim().matches(Regex(".*[A-Z].*"))
        }
        // Validasi adanya huruf kecil setelah di-trim
        addConstraint("Password must contain at least one lowercase letter") {
            it.trim().matches(Regex(".*[a-z].*"))
        }
        // Validasi adanya digit setelah di-trim
        addConstraint("Password must contain at least one digit") {
            it.trim().matches(Regex(".*[0-9].*"))
        }
        // Validasi adanya karakter spesial setelah di-trim
        addConstraint("Password must contain at least one special character (@#\$%^&+=!)") {
            it.trim().matches(Regex(".*[@#\$%^&+=!].*"))
        }
    }
}



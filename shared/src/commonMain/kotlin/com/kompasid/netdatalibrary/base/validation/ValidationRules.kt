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

    // Contoh Input yang Dianggap Valid:
    // +6281234567890
    // 081234567890
    // 6281234567890
    // +60123456789 (Malaysia)
    // +14085551234 (US)
    val phoneValidation = Validation<String> {
        // Tidak boleh kosong
        addConstraint("Phone number must not be empty") {
            it.trim().isNotEmpty()
        }

        // Harus dimulai dengan +, 08, atau 62
        addConstraint("Phone number must start with +, 62, or 08") {
            val number = it.trim()
            number.startsWith("+") || number.startsWith("08") || number.startsWith("62")
        }

        // Hanya karakter angka (boleh + di awal)
        addConstraint("Phone number contains invalid characters") {
            it.trim().matches(Regex("^\\+?[0-9]{8,15}\$"))
        }

        // Validasi khusus Indonesia (optional tapi disarankan)
        addConstraint("Invalid Indonesian phone number") {
            val number = it.trim()
            !number.startsWith("08") || number.length in 10..13
        }
    }

}



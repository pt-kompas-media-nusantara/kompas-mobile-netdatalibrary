package com.kompasid.netdatalibrary.base

import kotlinx.datetime.Clock
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class DecodeJWT {

    companion object {
        private const val EXPIRATION_MARGIN_SECONDS = 30
    }

    // Dekode Base64 URL tanpa library eksternal
    private fun decodeBase64Url(input: String): ByteArray {
        val base64 = input.replace("-", "+").replace("_", "/")
            .padEnd((4 - input.length % 4) % 4 + input.length, '=')
        return base64ToByteArray(base64)
    }

    // Konversi string Base64 menjadi ByteArray
    private fun base64ToByteArray(base64: String): ByteArray {
        val alphabet =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray()
        val base64Map = alphabet.withIndex().associate { it.value to it.index }

        val cleanInput = base64.filter { it in base64Map.keys || it == '=' }
        val groups = cleanInput.chunked(4)

        val byteList = mutableListOf<Byte>()
        for (group in groups) {
            val bits = group.map { if (it == '=') 0 else base64Map[it]!! }
                .fold(0) { acc, value -> (acc shl 6) or value }
            byteList.add((bits shr 16 and 0xFF).toByte())
            if (group[2] != '=') byteList.add((bits shr 8 and 0xFF).toByte())
            if (group[3] != '=') byteList.add((bits and 0xFF).toByte())
        }
        return byteList.toByteArray()
    }


    private fun decodeJwt(jwt: String): JwtPayload {
        val parts = jwt.split(".")
        require(parts.size == 3) { "Invalid JWT token format" }

        // Dekode payload (part kedua)
        val payloadJson = decodeBase64Url(parts[1]).decodeToString()

        // Parsing JSON ke data class
        return Json.decodeFromString(payloadJson)
    }

    suspend fun isTokenExpired(token: String): Boolean {
        if (token.isEmpty()) return true

        val exp = decodeJwt(token).exp

        // Tambahkan margin waktu (10 detik) untuk menghindari masalah delay
        val currentTime = Clock.System.now().epochSeconds
        return currentTime >= exp - EXPIRATION_MARGIN_SECONDS
    }
}

// Dekode JWT token
@Serializable
data class JwtPayload(
    val data: PayloadData,
    val exp: Long,
    val iat: Long,
    val iss: String
)

@Serializable
data class PayloadData(
    val email: String,
    val id: String,
    val rt: Long,
    val scope: List<String>
)
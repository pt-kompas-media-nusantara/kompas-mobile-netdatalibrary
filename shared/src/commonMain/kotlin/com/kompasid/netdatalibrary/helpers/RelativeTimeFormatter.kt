package com.kompasid.netdatalibrary.helpers

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone

class RelativeTimeFormatter {

    fun getCurrentTime(): String {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString()
    }

    fun getRelativeTime(dateTimeString: String): String {
        // Parsing input string ke LocalDateTime
        val inputDateTime = LocalDateTime.parse(dateTimeString.replace(" ", "T"))

        // Dapatkan waktu saat ini di zona waktu default
        val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        // Hitung selisih antara waktu sekarang dan waktu input
        val duration =
            currentDateTime.toInstant(TimeZone.currentSystemDefault()) - inputDateTime.toInstant(
                TimeZone.currentSystemDefault()
            )
        val durationInSeconds = duration.inWholeSeconds

        return when {
            durationInSeconds < 60 -> "${durationInSeconds} detik yang lalu"
            durationInSeconds < 3600 -> "${durationInSeconds / 60} menit yang lalu"
            durationInSeconds < 86400 -> "${durationInSeconds / 3600} jam yang lalu"
            durationInSeconds < 172800 -> "${durationInSeconds / 86400} hari yang lalu"
            else -> {
                // Format manual untuk tanggal lebih dari 1 hari yang lalu
                val day = inputDateTime.date.dayOfMonth.toString().padStart(2, '0')
                val month =
                    inputDateTime.date.month.name.lowercase().replaceFirstChar { it.uppercase() }
                val year = inputDateTime.date.year
                val hour = inputDateTime.hour.toString().padStart(2, '0')
                val minute = inputDateTime.minute.toString().padStart(2, '0')
                "$day $month $year, $hour:$minute"
            }
        }
    }
}


/*
fun main() {
    val formatter = RelativeTimeFormatter()
    val result = formatter.getRelativeTime("2024-10-22 11:52:40")
    println(result) // Output akan berupa waktu relatif atau format tanggal sesuai logika
}
 */
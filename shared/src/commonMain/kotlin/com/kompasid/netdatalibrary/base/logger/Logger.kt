package com.kompasid.netdatalibrary.base.logger

import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// Logger object untuk mencetak log dengan kategori yang disederhanakan
object Logger {

    suspend fun info(tag: String? = null, message: () -> String) {
        Napier.i(tag = tag) { formatMessage(LoggerType.INFO, message()) }
    }

    suspend fun error(tag: String? = null, message: () -> String) {
        Napier.e(tag = tag) { formatMessage(LoggerType.ERROR, message()) }
    }

    suspend fun warning(tag: String? = null, message: () -> String) {
        Napier.w(tag = tag) { formatMessage(LoggerType.WARNING, message()) }
    }

    suspend fun debug(tag: String? = null, message: () -> String) {
        Napier.d(tag = tag) { formatMessage(LoggerType.DEBUG, message()) }
    }

    suspend fun notification(tag: String? = null, message: () -> String) {
        Napier.i(tag = tag) { formatMessage(LoggerType.NOTIFICATION, message()) }
    }

    suspend fun url(tag: String? = null, message: () -> String) {
        Napier.d(tag = tag) { formatMessage(LoggerType.URL, message()) }
    }

    suspend fun response(tag: String? = null, message: () -> String) {
        Napier.d(tag = tag) { formatMessage(LoggerType.RESPONSE, message()) }
    }

    private suspend fun formatMessage(type: LoggerType, message: String): String {
        return "[${getCurrentTime()}] ${type.label} : $message"
    }

    private suspend fun getCurrentTime(): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return "${now.year}-${now.monthNumber}-${now.dayOfMonth} ${now.hour}:${now.minute}:${now.second}"
    }
}

/*
    Logger.info { "This is an informational message" }
    Logger.error { "An error occurred" }
    Logger.warning { "This is a warning message" }
    Logger.debug { "Debugging the app" }
    Logger.notification { "Notification sent successfully" }
    Logger.url { "Requesting URL: https://example.com" }
    Logger.response { "Received response with status 200" }
* */

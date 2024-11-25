package com.kompasid.netdatalibrary.base.logger

import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// Logger object untuk mencetak log dengan kategori yang disederhanakan
object Logger {

    fun info(tag: String? = null, message: () -> String) {
        Napier.i(tag = tag) { formatMessage(LoggerType.INFO, message()) }
    }

    fun error(tag: String? = null, message: () -> String) {
        Napier.e(tag = tag) { formatMessage(LoggerType.ERROR, message()) }
    }

    fun warning(tag: String? = null, message: () -> String) {
        Napier.w(tag = tag) { formatMessage(LoggerType.WARNING, message()) }
    }

    fun debug(tag: String? = null, message: () -> String) {
        Napier.d(tag = tag) { formatMessage(LoggerType.DEBUG, message()) }
    }

    fun notification(tag: String? = null, message: () -> String) {
        Napier.i(tag = tag) { formatMessage(LoggerType.NOTIFICATION, message()) }
    }

    fun url(tag: String? = null, message: () -> String) {
        Napier.d(tag = tag) { formatMessage(LoggerType.URL, message()) }
    }

    fun response(tag: String? = null, message: () -> String) {
        Napier.d(tag = tag) { formatMessage(LoggerType.RESPONSE, message()) }
    }

    private fun formatMessage(type: LoggerType, message: String): String {
        return "[${getCurrentTime()}] ${type.label} : $message"
    }

    private fun getCurrentTime(): String {
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

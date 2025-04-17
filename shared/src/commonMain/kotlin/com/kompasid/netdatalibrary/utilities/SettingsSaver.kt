package com.kompasid.netdatalibrary.utilities

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class SettingsSaver(
    private val settingsHelper: SettingsHelper,
    private val logger: (String, Throwable?) -> Unit = { msg, err -> println("$msg\n${err?.stackTraceToString()}") }
) {
    private suspend fun saveWithRetry(
        key: KeySettingsType,
        value: Any?,
        maxRetries: Int = 1,
        delayMillis: Long = 200L
    ) {
        repeat(maxRetries) { attempt ->
            try {
                settingsHelper.save(key, value)
                return
            } catch (e: Exception) {
                if (attempt == maxRetries - 1) {
                    logger("❌ Failed to save key=$key, value=$value", e)
                } else {
                    delay(delayMillis)
                }
            }
        }
    }

    fun saveAsync(
        scope: CoroutineScope,
        key: KeySettingsType,
        value: Any?
    ): Deferred<Unit> = scope.async {
        saveWithRetry(key, value)
    }

}

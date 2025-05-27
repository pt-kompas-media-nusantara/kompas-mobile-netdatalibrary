package com.kompasid.netdatalibrary

import android.content.res.Resources
import android.os.Build
import android.util.Log
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlin.math.round

actual class Platform() {
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()

    actual suspend fun logSystemInfo() {
        Log.d(
            "Daily Pulse",
            "($osName, $osVersion, $deviceModel, $density)"
        )
    }
}


actual class LoggerWrapper {
    actual fun initLogger() {
        Napier.base(DebugAntilog())
    }
}
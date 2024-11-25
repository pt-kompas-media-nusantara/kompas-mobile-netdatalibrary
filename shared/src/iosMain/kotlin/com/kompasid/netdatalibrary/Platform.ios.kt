package com.kompasid.netdatalibrary

import platform.Foundation.NSLog
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual class Platform() {
    actual val osName: String
        get() = UIDevice.currentDevice.systemName
    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    actual val deviceModel: String
        get() = UIDevice.currentDevice.model
    actual val density: Int
        get() = UIScreen.mainScreen.scale.toInt()

    actual fun logSystemInfo() {
        NSLog(
            "($osName, $osVersion, $deviceModel, $density)"
        )
    }
}
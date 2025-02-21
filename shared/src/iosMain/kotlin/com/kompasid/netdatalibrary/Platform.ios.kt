package com.kompasid.netdatalibrary

import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.OpenFromEntryPoint
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

    actual suspend fun logSystemInfo() {
        NSLog(
            "($osName, $osVersion, $deviceModel, $density)"
        )
    }
}
//
//// Implementasi actual untuk iOS
//actual class OpenFromEntryPointWrapper actual constructor() {
//    actual fun fromString(value: String): OpenFromEntryPoint? {
//        return when (value.uppercase()) {
//            "EMPTY" -> OpenFromEntryPoint.Empty
//            "HOT_TOPIC_SECTION" -> OpenFromEntryPoint.HotTopicSection
//            else -> null
//        }
//    }
//}

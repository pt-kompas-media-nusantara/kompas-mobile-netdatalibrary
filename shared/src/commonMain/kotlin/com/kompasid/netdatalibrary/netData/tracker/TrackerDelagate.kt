package com.kompasid.netdatalibrary.netData.tracker

// Reference : https://medium.com/@afonso.script.sol/a-step-by-step-guide-to-the-delegate-pattern-in-swift-91a28de1baf8

// commonMain
interface ITrackerDelegate {

    suspend fun trackEvent(eventName: EventName, eventProperty: Map<String, Any>)
}

enum class EventName(val value: String) {
    EXAMPLE("EXAMPLE"),
}

//class TrackerManager {
//    var delegate: ITrackerDelegate? = null
//
//    suspend fun trackEvent(eventName: EventName, eventProperty: Map<String, Any>) {
//        delegate?.trackEvent(eventName, eventProperty)
//    }
//}

object TrackerManager {
    private val listeners = mutableListOf<(String) -> Unit>()

    fun register(listener: (String) -> Unit) {
        listeners.add(listener)
    }

    fun post(event: String) {
        listeners.forEach { it(event) }
    }
}



//// androidMain
//class AndroidTrackerDelegate : TrackerDelegate {
//    override fun sendValueTracker(value: String) {
//        Log.d("Tracker", value)
//        // Kirim data ke Android Native
//    }
//}

//// Set delegate di Android
//TrackerManager.delegate = AndroidTrackerDelegate()
//
//// Contoh pemanggilan
//TrackerManager.trackEvent(mapOf("event_name" to "AppOpened", "timestamp" to System.currentTimeMillis()))
//

//
//// iosMain
//class IosTrackerDelegate: TrackerDelegate {
//    func sendValueTracker(value: String) {
//        print("Tracker: \(value)")
//        // Kirim data ke iOS Native
//    }
//}

//// Set delegate di iOS
//TrackerManager.shared.delegate = IosTrackerDelegate()
//
//// Contoh pemanggilan
//TrackerManager.shared.trackEvent(data: ["event_name": "AppOpened", "timestamp": Date().timeIntervalSince1970])

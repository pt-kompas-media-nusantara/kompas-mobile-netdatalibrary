package com.kompasid.netdatalibrary.netData.domain.trackerDomain

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.AboutKompasDailyModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.ExampleModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.SignUpStartedModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

object TrackerManager {
    private val listeners = mutableSetOf<(EventName, Map<String, Any>) -> Unit>()
    private val eventQueue =
        mutableListOf<Pair<EventName, Map<String, Any>>>() // Buffer untuk event yang belum terkirim

    fun register(listener: (eventName: EventName, eventProperty: Map<String, Any>) -> Unit) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }

        // Kirim event yang belum diproses sebelumnya
        if (eventQueue.isNotEmpty()) {
            eventQueue.forEach { (eventName, eventProperties) ->
                listener(eventName, eventProperties)
            }
            eventQueue.clear() // Kosongkan event queue setelah dikirim
        }
    }

    fun post(eventName: EventName, eventProperty: Any, userDataTrackerModel: UserDataTrackerModel) {
        val eventProperties = when (eventName) {
            EventName.EXAMPLE -> {
                if (eventProperty is ExampleModel) {
                    mapOf("example_id" to eventProperty.example)
                } else throw IllegalArgumentException("Invalid model for EventName.EXAMPLE")
            }

            EventName.SIGN_UP_STARTED -> {
                if (eventProperty is SignUpStartedModel) {
                    mapOf(
                        "sign_up_trigger" to eventProperty.signUpTrigger,
                        "content_id" to eventProperty.contentId,
                        "content_title" to eventProperty.contentTitle,
                        "content_categories" to eventProperty.contentCategories,
                        "content_type" to eventProperty.contentType,
                        "user_type" to userDataTrackerModel.user_type,
                        "subscription_status" to userDataTrackerModel.subscription_status,
                        "metered_wall_type" to userDataTrackerModel.metered_wall_type,
                        "metered_wall_balance" to userDataTrackerModel.metered_wall_balance,
                        "page_domain" to userDataTrackerModel.page_domain
                    )
                } else throw IllegalArgumentException("Invalid model for EventName.SIGN_UP_STARTED")
            }

            EventName.PAGE_VIEWED -> {
                if (eventProperty is AboutKompasDailyModel) {
                    mapOf(
                        "page_title" to eventProperty.page_title,
                        "user_type" to userDataTrackerModel.user_type,
                        "subscription_status" to userDataTrackerModel.subscription_status,
                        "metered_wall_type" to userDataTrackerModel.metered_wall_type,
                        "metered_wall_balance" to userDataTrackerModel.metered_wall_balance,
                        "page_domain" to userDataTrackerModel.page_domain
                    )
                } else throw IllegalArgumentException("Invalid model for EventName.PAGE_VIEWED")
            }
        }

        if (listeners.isNotEmpty()) {
            listeners.forEach { it(eventName, eventProperties) }
        } else {
            eventQueue.add(eventName to eventProperties) // Simpan event jika belum ada listener
        }
    }
}

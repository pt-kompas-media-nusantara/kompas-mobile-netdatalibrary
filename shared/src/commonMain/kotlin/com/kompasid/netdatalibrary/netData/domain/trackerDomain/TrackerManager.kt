package com.kompasid.netdatalibrary.netData.domain.trackerDomain

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.AboutKompasDailyModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.ExampleModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.SignUpStartedModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

object TrackerManager {
    private val listeners =
        mutableListOf<(eventName: EventName, eventProperty: Map<String, Any>) -> Unit>()

    // Mendaftarkan listener
    fun register(listener: (eventName: EventName, eventProperty: Map<String, Any>) -> Unit) {
        listeners.add(listener)
    }

    // Posting event dengan logika konversi berdasarkan model
    fun post(eventName: EventName, eventProperty: Any, userDataTrackerModel: UserDataTrackerModel) {
        Logger.debug { eventName.toString() }
        Logger.debug { eventProperty.toString() }
        Logger.debug { userDataTrackerModel.toString() }

        // TODO: Wahyu - Fungsi ini bakal panjang banget karena tracker itu ada banyak, untuk mappingnya
        //  gimana kalau misalkan di masing-masing data classnya aja contoh untuk event SIGN_UP_STARTED & EXAMPLE jadinya gini

//        val eventPropertiesSample = try {
//            when(eventName) {
//                EventName.SIGN_UP_STARTED -> {
//                    (eventProperty as SignUpStartedModel).getTrackerProperties(userDataTrackerModel)
//                }
//                EventName.EXAMPLE -> {
//                    (eventProperty as ExampleModel).getTrackerProperties()
//                }
//                else -> {
//                    mapOf()
//                }
//            }
//        }catch (e: Exception){
//            throw IllegalArgumentException("Invalid model for $eventName")
//        }

        val eventProperties = when (eventName) {
            EventName.EXAMPLE -> {
                if (eventProperty is ExampleModel) {
                    mapOf(
                        "example_id" to eventProperty.example
                    )
                } else {
                    throw IllegalArgumentException("Invalid model for EventName.EXAMPLE")
                }
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
                } else {
                    throw IllegalArgumentException("Invalid model for EventName.SIGN_UP_STARTED")
                }
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
                } else {
                    throw IllegalArgumentException("Invalid model for EventName.PAGE_VIEWED")
                }
            }
        }

        // Memanggil semua listener dengan properti event yang sudah diproses
        listeners.forEach { it(eventName, eventProperties) }
    }
}

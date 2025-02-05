package com.kompasid.netdatalibrary.netData.domain.trackerDomain.model

import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

data class ExampleModel(
    val example: String,
) {
    fun getTrackerProperties(): Map<String, Any> {
        return mapOf("example_id" to example)
    }
}
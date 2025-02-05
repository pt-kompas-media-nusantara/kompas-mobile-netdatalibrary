package com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base



data class UserDataTrackerModel(
    val user_type: String,
    val subscription_status: String,
    val metered_wall_type: String,
    val metered_wall_balance: String,
    val page_domain: String,
    val subscription_package: String
)
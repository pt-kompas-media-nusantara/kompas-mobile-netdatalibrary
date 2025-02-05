package com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base


// TODO: Wahyu - Penamaan variabel ini perlu di standarin mas mau pakai camel case atau snake case
//  klo biasa kotlin pake camel, snake itu biasa di JSON
data class UserDataTrackerModel(
    val user_type: String,
    val subscription_status: String,
    val metered_wall_type: String,
    val metered_wall_balance: String,
    val page_domain: String,
    val subscription_package: String
)
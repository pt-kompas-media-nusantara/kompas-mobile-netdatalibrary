package com.kompasid.netdatalibrary.netData.domain.trackerDomain.model

data class SignUpStartedModel(
    val signUpTrigger: String,
    val contentId: String,
    val contentTitle: String,
    val contentCategories: String,
    val contentType: String,
    val userType: String,
    val subscriptionStatus: String,
    val meteredWallType: String,
    val meteredWallBalance: String,
    val pageDomain: String
)
package com.kompasid.netdatalibrary.netData.domain.trackerDomain.model

import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.AuthenticationEntryPoint
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.StateSubscriptionType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.UserType

data class SignUpStartedModel(
    val signUpTrigger: AuthenticationEntryPoint,
    val contentId: String,
    val contentTitle: String,
    val contentCategories: String,
    val contentType: String,
    val userType: UserType,
    val subscriptionStatus: StateSubscriptionType,
    val meteredWallType: String = "ambil dari db",
    val meteredWallBalance: String = "ambil dari db",
    val pageDomain: String = "Kompas.id"
)
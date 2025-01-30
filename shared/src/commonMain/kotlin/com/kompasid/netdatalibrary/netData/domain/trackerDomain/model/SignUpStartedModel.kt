package com.kompasid.netdatalibrary.netData.domain.trackerDomain.model

import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.AuthenticationEntryPoint
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

data class SignUpStartedModel(
    val signUpTrigger: AuthenticationEntryPoint,
    val contentId: String,
    val contentTitle: String,
    val contentCategories: String,
    val contentType: String,
)
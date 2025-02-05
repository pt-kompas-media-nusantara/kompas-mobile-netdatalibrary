package com.kompasid.netdatalibrary.netData.domain.trackerDomain.model

import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.AuthenticationEntryPoint
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

data class SignUpStartedModel(
    val signUpTrigger: AuthenticationEntryPoint,
    val contentId: String,
    val contentTitle: String,
    val contentCategories: String,
    val contentType: String,
) {
    // TODO: Wahyu - Ini sample mapper trackernya
    fun getTrackerProperties(userDataTrackerModel: UserDataTrackerModel): Map<String, Any> {
        return mapOf(
            "sign_up_trigger" to signUpTrigger,
            "content_id" to contentId,
            "content_title" to contentTitle,
            "content_categories" to contentCategories,
            "content_type" to contentType,
            "user_type" to userDataTrackerModel.user_type,
            "subscription_status" to userDataTrackerModel.subscription_status,
            "metered_wall_type" to userDataTrackerModel.metered_wall_type,
            "metered_wall_balance" to userDataTrackerModel.metered_wall_balance,
            "page_domain" to userDataTrackerModel.page_domain
        )
    }
}
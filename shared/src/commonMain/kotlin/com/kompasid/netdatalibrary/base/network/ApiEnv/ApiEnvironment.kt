package com.kompasid.netdatalibrary.base.network.ApiEnv

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class ApiEnvironment(
    private val settingsHelper: SettingsHelper
){

    suspend fun getLogoutUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.LOGOUT_URL_PROD
            else -> ApiConfig.LOGOUT_URL_DEV
        }
    }

    suspend fun getLoginByEmailUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.LOGIN_BY_EMAIL_URL_PROD
            else -> ApiConfig.LOGIN_BY_EMAIL_URL_DEV
        }
    }

    suspend fun getLoginByGoogleUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.LOGIN_BY_GOOGLE_URL_PROD
            else -> ApiConfig.LOGIN_BY_GOOGLE_URL_DEV
        }
    }

    suspend fun getLoginByAppleUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.LOGIN_BY_APPLE_URL_PROD
            else -> ApiConfig.LOGIN_BY_APPLE_URL_DEV
        }
    }

    suspend fun getLoginByPurchaseTokenUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.LOGIN_BY_PURCHASE_TOKEN_URL_PROD
            else -> ApiConfig.LOGIN_BY_PURCHASE_TOKEN_URL_DEV
        }
    }

    suspend fun getRegisterByEmailUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.REGISTER_URL_PROD
            else -> ApiConfig.REGISTER_URL_DEV
        }
    }

    suspend fun getCheckRegisteredUsersUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.CHECK_VERIFIED_USER_URL_PROD
            else -> ApiConfig.CHECK_VERIFIED_USER_URL_DEV
        }
    }

    suspend fun getOsRecommendationUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.OS_RECOMMENDATION_PROD
            else -> ApiConfig.OS_RECOMMENDATION_DEV
        }
    }

    suspend fun getUserDetailUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.USER_DETAIL_URL_PROD
            else -> ApiConfig.USER_DETAIL_URL_DEV
        }
    }

    suspend fun getUserHistoryMembershipUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.USER_HISTORY_MEMBERSHIP_URL_PROD
            else -> ApiConfig.USER_HISTORY_MEMBERSHIP_URL_DEV
        }
    }

    suspend fun getUserMembershipUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.USER_MEMBERSHIP_URL_PROD
            else -> ApiConfig.USER_MEMBERSHIP_URL_DEV
        }
    }

    suspend fun getForceUpdateUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.FORCE_UPDATE_PROD
            else -> ApiConfig.FORCE_UPDATE_DEV
        }
    }

    suspend fun getPurchaseTokenCheckUrl(): String {
        return when (flavors()) {
            FlavorsType.RELEASE -> ApiConfig.PURCHASE_TOKEN_CHECK_PROD
            else -> ApiConfig.PURCHASE_TOKEN_CHECK_DEV
        }
    }

    suspend fun flavors(): FlavorsType {
        val flavor = settingsHelper.get(KeySettingsType.FLAVORS, "")
        return when (flavor.uppercase()) {
            FlavorsConstant.DEBUG -> FlavorsType.DEBUG
            FlavorsConstant.ALLCLOUD -> FlavorsType.ALLCLOUD
            FlavorsConstant.DKID_ID -> FlavorsType.DKID_ID
            FlavorsConstant.DKID_CLOUD -> FlavorsType.DKID_CLOUD
            FlavorsConstant.DQA_ID -> FlavorsType.DQA_ID
            FlavorsConstant.DQA_CLOUD -> FlavorsType.DQA_CLOUD
            FlavorsConstant.RQA_ID -> FlavorsType.RQA_ID
            FlavorsConstant.RKID_ID -> FlavorsType.RKID_ID
            else -> FlavorsType.RELEASE
        }
    }

}





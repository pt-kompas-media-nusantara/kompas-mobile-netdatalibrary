package com.kompasid.netdatalibrary.base.network.ApiEnv

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class ApiEnvironment(
    private val settingsHelper: SettingsHelper
){

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





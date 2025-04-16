package com.kompasid.netdatalibrary.core.domain.osRecomendation.useCase

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.enums.OSRecommendationType
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.repository.OSRecomendationRepository
import com.kompasid.netdatalibrary.core.data.refreshToken.dto.request.RefreshTokenRequest
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.helpers.logged
import com.kompasid.netdatalibrary.helpers.times.RelativeTimeFormatter
import com.kompasid.netdatalibrary.helpers.times.ValidateTimeFormatter
import kotlinx.datetime.LocalDateTime

class OSRecomendationUseCase(
    private val osRecomendationRepository: OSRecomendationRepository,
    private val settingsHelper: SettingsHelper
) : IOSRecomendationUseCase {

    // ios: bisa di taruh di didFinishLaunchingWithOptions atau klik beranda
    suspend fun osRecommendation(): Results<OSRecommendationType, NetworkError> {
        return try {

            when (val result = osRecomendationRepository.osRecommendation().logged(prefix = "osRecommendation")) {
                is Results.Error -> {
                    Results.Error(result.error)
                }

                is Results.Success -> {
                    val now = RelativeTimeFormatter().getCurrentTime()

                    val lastInfo = settingsHelper.get(KeySettingsType.LAST_INFORMATION_SHOWN_DATE, "")
                    val lastRec = settingsHelper.get(KeySettingsType.LAST_RECOMMENDATION_SHOWN_DATE, "")
                    val osVersion = settingsHelper.get(KeySettingsType.OS_VERSION, "")
                    val isFirstInstall: Boolean = settingsHelper.get(KeySettingsType.IS_FIRST_INSTALL, false)

                    val osRecommendation = result.data.osRecommendation
                    val minimumOS = result.data.minimumOS

                    val current = ValidateOSVersion.parse(osVersion)
                    val min = ValidateOSVersion.parse(minimumOS)
                    val recommended = ValidateOSVersion.parse(osRecommendation)

                    if (lastInfo.isEmpty()) settingsHelper.save(KeySettingsType.LAST_INFORMATION_SHOWN_DATE, now)

                    if (lastRec.isEmpty()) settingsHelper.save(KeySettingsType.LAST_RECOMMENDATION_SHOWN_DATE, now)



                    return when {
                        current < min -> {
                            val recTime = ValidateTimeFormatter().calculateTimeDifferenceComponents(
                                try {
                                    LocalDateTime.parse(lastRec)
                                } catch (_: Exception) {
                                    LocalDateTime.parse(now)
                                }
                            )
                            if (recTime.months >= 3 || isFirstInstall) {
                                settingsHelper.save(KeySettingsType.LAST_RECOMMENDATION_SHOWN_DATE, now)
                                Results.Success(OSRecommendationType.OS_UPDATE_RECOMMENDATION)
                            } else {
                                Results.Success(OSRecommendationType.NO_UPDATE_OS)
                            }
                        }

                        current >= min && current < recommended -> {
                            val infoTime = ValidateTimeFormatter().calculateTimeDifferenceComponents(
                                try {
                                    LocalDateTime.parse(lastInfo)
                                } catch (_: Exception) {
                                    LocalDateTime.parse(now)
                                }
                            )
                            if (infoTime.months >= 1 || isFirstInstall) {
                                settingsHelper.save(KeySettingsType.LAST_INFORMATION_SHOWN_DATE, now)
                                Results.Success(OSRecommendationType.OS_UPDATE_INFORMATION)
                            } else {
                                Results.Success(OSRecommendationType.NO_UPDATE_OS)
                            }

                        }

                        else -> Results.Success(OSRecommendationType.NO_UPDATE_OS)
                    }
                }
            }

        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}
/*
{
  "osRecommendation": "17.0.0",
  "minimumOS": "16.0.0"
}
* */
package com.kompasid.netdatalibrary.core.domain.osRecomendation.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.enums.OSRecommendationType
import com.kompasid.netdatalibrary.core.data.osRecomendation.repository.OSRecomendationRepository
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.enums.StateInstallType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.helpers.logged
import com.kompasid.netdatalibrary.helpers.times.RelativeTimeFormatter
import com.kompasid.netdatalibrary.helpers.times.CalculateTimeFormatter
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.datetime.LocalDateTime

/// figma : https://www.figma.com/design/9J5e5MOrWDGYY2KqHrxqmy/OS-Recommendation?node-id=59-955&p=f&t=BJo9uemXqqxtDo7c-0
class OSRecomendationUseCase(
    private val osRecomendationRepository: OSRecomendationRepository,
    private val settingsHelper: SettingsHelper,
    private val supportSettingsHelper: SupportSettingsHelper
) : IOSRecomendationUseCase {

    suspend fun closeClick(type: OSRecommendationType) {
        val now = RelativeTimeFormatter().getCurrentTime()
        when (type) {
            OSRecommendationType.NO_UPDATE_OS -> TODO()
            OSRecommendationType.OS_UPDATE_INFORMATION -> settingsHelper.save(KeySettingsType.LAST_INFORMATION_SHOWN_DATE, now)
            OSRecommendationType.OS_UPDATE_RECOMMENDATION -> settingsHelper.save(KeySettingsType.LAST_RECOMMENDATION_SHOWN_DATE, now)
        }
    }

    suspend fun closeClick(minOS: String, recoOS: String, currentOS: String) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.MINIMUM_APP_VERSION_KOMPAS_ID_TEMP, minOS),
                settingsHelper.saveAsync(this, KeySettingsType.MAXIMUM_APP_VERSION_KOMPAS_ID_TEMP, recoOS),
                settingsHelper.saveAsync(this, KeySettingsType.CURRENT_APP_VERSION_KOMPAS_ID_TEMP, currentOS)
            ).awaitAll()
        }
    }

    // ketika klik bottom bar : beranda, epaper, ebook, akun
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
                    val isDebug = settingsHelper.get(KeySettingsType.IS_DEBUG, false)

                    val osVersion = if (isDebug) {
                        result.data.osVersion
                    } else {
                        settingsHelper.get(KeySettingsType.OS_VERSION, "")
                    }

                    val osRecommendation = result.data.osRecommendation
                    val minimumOS = result.data.minimumOS

                    val current = ValidateOSVersion.parse(osVersion)
                    val recommended = ValidateOSVersion.parse(osRecommendation)
                    val min = ValidateOSVersion.parse(minimumOS)

                    if (lastInfo.isEmpty()) settingsHelper.save(KeySettingsType.LAST_INFORMATION_SHOWN_DATE, now)

                    if (lastRec.isEmpty()) settingsHelper.save(KeySettingsType.LAST_RECOMMENDATION_SHOWN_DATE, now)


                    return when {
                        // OS_UPDATE_INFORMATION | di bawah 16
                        current < min -> {
                            val infoTime = CalculateTimeFormatter().calculateTimeDifferenceComponents(
                                try {
                                    LocalDateTime.parse(lastInfo)
                                } catch (_: Exception) {
                                    LocalDateTime.parse(now)
                                }
                            )
                            if (infoTime.months >= 1) {
                                settingsHelper.save(KeySettingsType.LAST_RECOMMENDATION_SHOWN_DATE, now)
                                Results.Success(OSRecommendationType.OS_UPDATE_RECOMMENDATION)
                            } else {
                                Results.Success(OSRecommendationType.NO_UPDATE_OS)
                            }
                        }

                        // OS_UPDATE_INFORMATION
                        current >= min && current < recommended -> {
                            val infoTime = CalculateTimeFormatter().calculateTimeDifferenceComponents(
                                try {
                                    LocalDateTime.parse(lastInfo)
                                } catch (_: Exception) {
                                    LocalDateTime.parse(now)
                                }
                            )
                            if (infoTime.months >= 1) {
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
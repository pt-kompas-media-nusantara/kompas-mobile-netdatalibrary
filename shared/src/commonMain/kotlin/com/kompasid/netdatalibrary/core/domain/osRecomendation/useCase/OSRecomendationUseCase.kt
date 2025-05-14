package com.kompasid.netdatalibrary.core.domain.osRecomendation.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.osRecomendation.model.OSRecommendationType
import com.kompasid.netdatalibrary.core.data.osRecomendation.repository.OSRecomendationRepository
import com.kompasid.netdatalibrary.core.domain.osRecomendation.model.OSRecommendationInterceptor
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.helpers.logged
import com.kompasid.netdatalibrary.helpers.times.RelativeTimeFormatter
import com.kompasid.netdatalibrary.helpers.times.CalculateTimeFormatter
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.datetime.LocalDateTime

/// figma : https://www.figma.com/design/9J5e5MOrWDGYY2KqHrxqmy/OS-Recommendation?node-id=59-956&t=VsS9fOaXFf2Iom1N-1
class OSRecomendationUseCase(
    private val osRecomendationRepository: OSRecomendationRepository,
    private val settingsHelper: SettingsHelper,
    private val supportSettingsHelper: SupportSettingsHelper
) : IOSRecomendationUseCase {

    suspend fun closeClick(minOS: String, recoOS: String) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.MINIMUM_OS_TEMP, minOS),
                settingsHelper.saveAsync(this, KeySettingsType.RECOMMENDATION_OS_TEMP, recoOS),
            ).awaitAll()
        }
    }

    // ketika klik bottom bar : beranda, epaper, ebook, akun
    suspend fun osRecommendation(): Results<OSRecommendationInterceptor, NetworkError> {
        return try {

            when (val result = osRecomendationRepository.osRecommendation().logged(prefix = "UseCase: osRecommendation")) {
                is Results.Error -> {
                    Results.Error(result.error)
                }

                is Results.Success -> {
                    val now = RelativeTimeFormatter().getCurrentTime()

                    val lastInfo = settingsHelper.get(KeySettingsType.LAST_MINIMUM_OS_SHOWN_DATE, "")
                    val lastRec = settingsHelper.get(KeySettingsType.LAST_RECOMMENDATION_OS_SHOWN_DATE, "")
                    val isDebug = settingsHelper.get(KeySettingsType.IS_DEBUG, false)

                    val osVersion = if (isDebug) {
                        result.data.ios.osVersion
                    } else {
                        settingsHelper.get(KeySettingsType.OS_VERSION, "")
                    }

                    val osRecommendation = result.data.ios.osRecommendation
                    val minimumOS = result.data.ios.minimumOS

                    val current = ValidateOSVersion.parse(osVersion)
                    val recommended = ValidateOSVersion.parse(osRecommendation)
                    val min = ValidateOSVersion.parse(minimumOS)

                    if (lastInfo.isEmpty()) settingsHelper.save(KeySettingsType.LAST_MINIMUM_OS_SHOWN_DATE, now)

                    if (lastRec.isEmpty()) settingsHelper.save(KeySettingsType.LAST_RECOMMENDATION_OS_SHOWN_DATE, now)

                    // Data sementara untuk mengecek apakah popup sudah muncul
                    val minOSTemp = settingsHelper.get(KeySettingsType.MINIMUM_OS_TEMP, "")
                    val recoOSTemp = settingsHelper.get(KeySettingsType.RECOMMENDATION_OS_TEMP, "")

                    val alreadyPromptedMinOS =
                        minOSTemp == minimumOS
                    val alreadyPromptedRecoOS =
                        recoOSTemp == osRecommendation


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
                            if (infoTime.months >= 1 || !alreadyPromptedMinOS) {
                                settingsHelper.save(KeySettingsType.LAST_MINIMUM_OS_SHOWN_DATE, now)
                                Results.Success(
                                    OSRecommendationInterceptor(
                                        title = result.data.ios.userInterface.osInformation.title,
                                        descriptiion = result.data.ios.userInterface.osInformation.descriptiion,
                                        minOS = minimumOS,
                                        recoOS = osRecommendation,
                                        type = OSRecommendationType.OS_UPDATE_INFORMATION,
                                    )
                                )
                            } else {
                                Results.Success(
                                    OSRecommendationInterceptor(
                                        title = "",
                                        descriptiion = "",
                                        minOS = "",
                                        recoOS = "",
                                        type = OSRecommendationType.NO_UPDATE_OS,
                                    )
                                )
                            }
                        }

                        // OS_UPDATE_RECOMMENDATION | di atas 16
                        current >= min && current < recommended -> {
                            val recoTime = CalculateTimeFormatter().calculateTimeDifferenceComponents(
                                try {
                                    LocalDateTime.parse(lastRec)
                                } catch (_: Exception) {
                                    LocalDateTime.parse(now)
                                }
                            )
                            if (recoTime.months >= 3 || !alreadyPromptedRecoOS) {
                                settingsHelper.save(KeySettingsType.LAST_RECOMMENDATION_OS_SHOWN_DATE, now)
                                Results.Success(
                                    OSRecommendationInterceptor(
                                        title = result.data.ios.userInterface.osRecomendation.title,
                                        descriptiion = result.data.ios.userInterface.osRecomendation.descriptiion,
                                        minOS = minimumOS,
                                        recoOS = osRecommendation,
                                        type = OSRecommendationType.OS_UPDATE_RECOMMENDATION,
                                    )
                                )
                            } else {
                                Results.Success(
                                    OSRecommendationInterceptor(
                                        title = "",
                                        descriptiion = "",
                                        minOS = "",
                                        recoOS = "",
                                        type = OSRecommendationType.NO_UPDATE_OS,
                                    )
                                )
                            }
                        }

                        else -> {
                            Results.Success(
                                OSRecommendationInterceptor(
                                    title = "",
                                    descriptiion = "",
                                    minOS = "",
                                    recoOS = "",
                                    type = OSRecommendationType.NO_UPDATE_OS,
                                )
                            )
                        }
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




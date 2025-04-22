package com.kompasid.netdatalibrary.core.domain.forceUpdate.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.enums.ForceUpdateType
import com.kompasid.netdatalibrary.core.data.forceUpdate.repository.ForceUpdateRepository
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.enums.StateInstallType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.helpers.logged
import com.kompasid.netdatalibrary.helpers.times.CalculateTimeFormatter
import com.kompasid.netdatalibrary.helpers.times.RelativeTimeFormatter
import kotlinx.datetime.LocalDateTime

class ForceUpdateUseCase(
    private val forceUpdateRepository: ForceUpdateRepository,
    private val settingsHelper: SettingsHelper,
    private val supportSettingsHelper: SupportSettingsHelper
) {
    suspend fun forceUpdate(): Results<ForceUpdateType, NetworkError> {
        return try {

            when (val result = forceUpdateRepository.forceUpdate().logged(prefix = "forceUpdate")) {
                is Results.Error -> {
                    Results.Error(result.error)
                }

                is Results.Success -> {
                    val now = RelativeTimeFormatter().getCurrentTime()

                    val lastForceUpdate = settingsHelper.get(KeySettingsType.LAST_FORCE_UPDATE_SHOWN_DATE, "")

                    val appVersions: List<String> = settingsHelper.get(KeySettingsType.APP_VERSIONS_KOMPAS_ID, emptyList())
                    val maxVersion = result.data.maxVersion
                    val minVersion = result.data.minVersion

                    val current = ValidateOSVersion.parse(appVersions.last())
                    val max = ValidateOSVersion.parse(maxVersion)
                    val min = ValidateOSVersion.parse(minVersion)

                    if (lastForceUpdate.isEmpty()) settingsHelper.save(KeySettingsType.LAST_FORCE_UPDATE_SHOWN_DATE, now)

                    val shownTime = CalculateTimeFormatter().calculateTimeDifferenceComponents(
                        try {
                            LocalDateTime.parse(lastForceUpdate)
                        } catch (_: Exception) {
                            LocalDateTime.parse(now)
                        }
                    )

                    if (current >= max) {
                        settingsHelper.save(KeySettingsType.APP_VERSION_KOMPAS_ID_API, current)
                    } else {
                        settingsHelper.save(KeySettingsType.APP_VERSION_KOMPAS_ID_API, max)
                    }

                    return when {
                        // minor update
                        current >= min && current < max -> {
                            if (shownTime.days >= 1 || supportSettingsHelper.stateInstallType() == StateInstallType.FIRST_INSTALL) {
                                settingsHelper.save(KeySettingsType.LAST_FORCE_UPDATE_SHOWN_DATE, now)
                                Results.Success(ForceUpdateType.MINOR_UPDATE)
                            } else {
                                Results.Success(ForceUpdateType.NO_UPDATE)
                            }
                        }

                        // major update
                        current < min -> {
                            if (shownTime.days >= 1 || supportSettingsHelper.stateInstallType() == StateInstallType.FIRST_INSTALL) {
                                settingsHelper.save(KeySettingsType.LAST_FORCE_UPDATE_SHOWN_DATE, now)
                                Results.Success(ForceUpdateType.MAJOR_UPDATE)
                            } else {
                                Results.Success(ForceUpdateType.NO_UPDATE)
                            }

                        }

                        else -> Results.Success(ForceUpdateType.NO_UPDATE)
                    }
                }
            }

        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}
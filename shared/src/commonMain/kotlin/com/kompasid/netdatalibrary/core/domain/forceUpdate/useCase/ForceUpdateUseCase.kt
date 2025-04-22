package com.kompasid.netdatalibrary.core.domain.forceUpdate.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.enums.ForceUpdateType
import com.kompasid.netdatalibrary.core.data.forceUpdate.repository.ForceUpdateRepository
import com.kompasid.netdatalibrary.core.domain.forceUpdate.model.MinMaxVersionAppInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.helpers.logged
import com.kompasid.netdatalibrary.utilities.Constants
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/*
figma :
https://www.figma.com/design/KuYSCw9BysUMidLfcsx8Gs/iPadOS---Akun---Misc?node-id=717-39578&p=f&t=i9Qdp0lbzXUXjVda-0
https://www.figma.com/design/6mp9LQv56mUAqKXDaO60on/iOS---Akun---Misc?node-id=5540-403782&t=YalRIX6gBySOM43y-0
*/
class ForceUpdateUseCase(
    private val forceUpdateRepository: ForceUpdateRepository,
    private val settingsHelper: SettingsHelper,
) {

    suspend fun updateLater(minVersion: String, maxVersion: String, appVersion: String) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.MINIMUM_APP_VERSION_KOMPAS_ID_TEMP, minVersion),
                settingsHelper.saveAsync(this, KeySettingsType.MAXIMUM_APP_VERSION_KOMPAS_ID_TEMP, maxVersion),
                settingsHelper.saveAsync(this, KeySettingsType.CURRENT_APP_VERSION_KOMPAS_ID_TEMP, appVersion)
            ).awaitAll()
        }
    }

    // URL ke halaman App Store Kompas.id
    suspend fun updateNow(): String {
        return Constants.URL_APPSTORE_KOMPAS_ID
    }

    // ketika klik bottom bar : beranda, epaper, ebook, akun
    suspend fun forceUpdate(): Results<Pair<ForceUpdateType, MinMaxVersionAppInterceptor>, NetworkError> {
        return try {
            when (val result = forceUpdateRepository.forceUpdate().logged(prefix = "forceUpdate")) {
                is Results.Error -> Results.Error(result.error)

                is Results.Success -> {
                    val isDebug = settingsHelper.get(KeySettingsType.IS_DEBUG, false)

                    val minVersion = result.data.minVersion
                    val maxVersion = result.data.maxVersion
                    val versionInfo = MinMaxVersionAppInterceptor(minVersion, maxVersion)

                    val appVersions = if (isDebug) {
                        listOf(result.data.mobileVersion)
                    } else {
                        settingsHelper.get(KeySettingsType.APP_VERSIONS_KOMPAS_ID, emptyList())
                    }

                    val current = ValidateOSVersion.parse(appVersions.lastOrNull() ?: result.data.mobileVersion)
                    val min = ValidateOSVersion.parse(minVersion)
                    val max = ValidateOSVersion.parse(maxVersion)

                    // Simpan versi yang dipakai untuk validasi
                    val savedVersion = if (current >= max) current else max
                    settingsHelper.save(KeySettingsType.APP_VERSION_KOMPAS_ID_API, savedVersion)

                    // Data sementara untuk mengecek apakah popup sudah muncul
                    val minTemp = settingsHelper.get(KeySettingsType.MINIMUM_APP_VERSION_KOMPAS_ID_TEMP, "")
                    val maxTemp = settingsHelper.get(KeySettingsType.MAXIMUM_APP_VERSION_KOMPAS_ID_TEMP, "")
                    val currentTemp = settingsHelper.get(KeySettingsType.CURRENT_APP_VERSION_KOMPAS_ID_TEMP, "")
                    val currentVersionString = appVersions.lastOrNull() ?: ""

                    val alreadyPrompted =
                        minTemp == minVersion && maxTemp == maxVersion && currentTemp == currentVersionString

                    return when {
                        appVersions.contains(result.data.maxVersion) -> {
                            Results.Success(Pair(ForceUpdateType.NO_UPDATE, versionInfo))
                        }

                        current < min -> {
                            Results.Success(Pair(ForceUpdateType.MAJOR_UPDATE, versionInfo))
                        }

                        current >= min && current < max -> {
                            if (alreadyPrompted) Results.Success(Pair(ForceUpdateType.NO_UPDATE, versionInfo))
                            else Results.Success(Pair(ForceUpdateType.MINOR_UPDATE, versionInfo))
                        }

                        else -> Results.Success(Pair(ForceUpdateType.NO_UPDATE, versionInfo))
                    }
                }
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}


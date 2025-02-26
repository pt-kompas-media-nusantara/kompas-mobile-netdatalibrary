package com.kompasid.netdatalibrary.core.domain.aboutApp.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.interceptor.LoginInterceptor
import com.kompasid.netdatalibrary.core.domain.aboutApp.model.AboutAppInterceptor
import com.kompasid.netdatalibrary.core.domain.aboutApp.model.AppInfoModel
import com.kompasid.netdatalibrary.helper.UserDataHelper
import com.kompasid.netdatalibrary.helper.enums.StateUserType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.utilities.Constants
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class AboutAppResultState(
    private val settingsHelper: SettingsHelper,
    private val userDataHelper: UserDataHelper
) : BaseVM() {

    val currentVersionApp: StateFlow<String> =
        settingsHelper.load(KeySettingsType.CURRENT_VERSION_APP, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.Lazily, "")

    val flavors: StateFlow<String> = settingsHelper.load(KeySettingsType.FLAVORS, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.Lazily, "")

    val userGuid: StateFlow<String> = settingsHelper.load(KeySettingsType.USER_GUID, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.Lazily, "")

    val email: StateFlow<String> = settingsHelper.load(KeySettingsType.EMAIL, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.Lazily, "")

//    val originalTransactionId: StateFlow<String> =
//        settingsHelper.load(KeySettingsType.ORIGINAL_TRANSACTION_ID, "")
//            .distinctUntilChanged()
//            .stateIn(scope, SharingStarted.Lazily, "")

    // nurirppan__ : ini masih blm di save
//    val transactionId: StateFlow<List<String>> =
//        settingsHelper.load(KeySettingsType.TRANSACTION_ID, emptyList<String>())
//            .distinctUntilChanged()
//            .stateIn(scope, SharingStarted.Lazily, emptyList())

    val deviceType: StateFlow<String> =
        settingsHelper.load(KeySettingsType.DEVICE_TYPE, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.Lazily, "")

    val osVersion: StateFlow<String> =
        settingsHelper.load(KeySettingsType.OS_VERSION, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.Lazily, "")

    val newVersionApp: StateFlow<String> =
        settingsHelper.load(KeySettingsType.NEW_VERSION_APP, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.Lazily, "")

//    val historyTransaction: StateFlow<List<String>> =
//        settingsHelper.load(KeySettingsType.HISTORY_TRANSACTION, emptyList<String>())
//            .distinctUntilChanged()
//            .stateIn(scope, SharingStarted.Lazily, emptyList())

    val userState: StateFlow<String> = flow {
        emit(
            when (userDataHelper.checkUserType()) {
                StateUserType.ANON -> "Anonymous"
                StateUserType.REGON -> "Register Only"
                StateUserType.GRACE_PERIOD -> "Grace Period"
                StateUserType.SUBER -> "Subscriber"
            }
        )
    }
        .stateIn(scope, SharingStarted.Lazily, "Unknown")

    // 🔹 Combine pertama (Maksimal 5 parameter)
    private val combinePart1 = combine(
        currentVersionApp, flavors, email, userGuid
    ) { currentVersionApp, flavors, email, userGuid ->
        AboutAppInterceptor(
            appVersion = "$currentVersionApp - Kompas.id $flavors",
            desc = Constants.aboutAppContent,
            appInfoTitle = "Informasi Perangkat",
            appInfo = listOf(
                AppInfoModel("Email", email),
                AppInfoModel("GUID", userGuid),
                AppInfoModel("Original ID Transaksi", "originalTransactionId")
            )
        )
    }

    // 🔹 Combine kedua (Maksimal 5 parameter)
    private val combinePart2 = combine(
        deviceType, osVersion, newVersionApp
    ) { deviceType, osVersion, newVersionApp ->
        AboutAppInterceptor(
            appInfo = listOf(
                AppInfoModel("ID Transaksi", "transactionId"),
                AppInfoModel("Tipe Perangkat", deviceType),
                AppInfoModel("Versi OS", osVersion),
                AppInfoModel("Versi App Saat Ini", newVersionApp),
                AppInfoModel("Riwayat Langganan", "historyTransaction")
            )
        )
    }

    // 🔹 Combine hasil dari `combinePart1` dan `combinePart2`
    val data: StateFlow<AboutAppInterceptor> = combine(
        combinePart1, combinePart2, userState
    ) { part1, part2, userState ->
        part1.copy(
            appInfo = part1.appInfo +
                    listOf(AppInfoModel("Status User", userState)) +
                    part2.appInfo
        )
    }
        .distinctUntilChanged()
        .debounce(300)
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), AboutAppInterceptor())

}

/*
Informasi Perangkat Kompas id
Email: nur.irfan@kompas.com
Guid: 947baf96-67cd-45a7-ac30-e900651a96c0
Status User: paid
ID Transaksi Orisinal: 2000000228785301
ID Transaksi: 2000000439907139, 2000000439903540, 2000000439900300, 2000000439898117, 2000000439893721, 2000000439892158, 2000000439888936, 2000000439886859, 2000000439884267, 2000000439882992, 2000000439880033, 2000000439877409, 2000000324346929, 2000000324310949, 2000000324268208, 2000000324227982, 2000000324186589, 2000000324144453, 2000000324101705, 2000000324060696, 2000000324025099, 2000000323991317, 2000000323968684, 2000000323953035, 2000000323303128, 2000000323301524, 2000000323299907, 2000000323298817, 2000000323298193, 2000000323297256, 2000000323295836, 2000000323294971, 2000000323293959, 2000000323293324, 2000000323292573, 2000000323292120, 2000000285937262, 2000000285932918, 2000000285926660, 2000000285920787, 2000000285916985, 2000000285913130, 2000000285905670, 2000000285901884, 2000000285895168, 2000000285890062, 2000000285883164, 2000000285875521, 2000000282575254, 2000000282565514, 2000000282553875, 2000000282547171, 2000000282539429, 2000000282529196, 2000000282521873, 2000000282513521, 2000000282504739, 2000000282495210, 2000000282488826, 2000000282480523, 2000000228847519, 2000000228843609, 2000000228836691, 2000000228831152, 2000000228823719, 2000000228817699, 2000000228812786, 2000000228807629, 2000000228801778, 2000000228797096, 2000000228791765, 2000000228785301
Tipe Perangkat: iPhone10,6
Versi OS: 16.7.8
Versi Saat ini: 3.51.0
Versi Terbaru: 3.51.0
Riwayat Langganan:
ID Produk: id.kompas.app.sub.premium.onemonth
Tanggal Pembelian: 2023-10-20 05:02:13 +0000
ID Produk: id.kompas.app.sub.premium.onemonth
Tanggal Pembelian: 2023-10-20 04:57:04 +0000
ID Produk: id.kompas.app.sub.premium.onemonth
Tanggal Pembelian: 2023-10-20 04:52:04 +0000
ID Produk: id.kompas.app.sub.premium.onemonth
Tanggal Pembelian: 2023-10-20 04:47:04 +0000
ID Produk: id.kompas.app.sub.premium.onemonth
Tanggal Pembelian: 2023-10-20 04:42:04 +0000
*/

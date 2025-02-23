package com.kompasid.netdatalibrary.core.domain.aboutApp.resultState

import com.kompasid.netdatalibrary.core.domain.aboutApp.model.AboutAppModel
import com.kompasid.netdatalibrary.core.domain.aboutApp.model.AppInfoModel
import com.kompasid.netdatalibrary.core.presentation.personalInfo.resultState.PersonalInfoResultState
import com.kompasid.netdatalibrary.utilities.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutAppResultState(
    private val personalInfoResultState: PersonalInfoResultState
) {
    private val _guid = MutableStateFlow<String>("")
    var guid: StateFlow<String> = _guid.asStateFlow()

    private val _userType = MutableStateFlow<String>("")
    var userType: StateFlow<String> = _userType.asStateFlow()

    private val _transactionId = MutableStateFlow<String>("")
    var transactionId: StateFlow<String> = _transactionId.asStateFlow()

    private val _originalTransactionId = MutableStateFlow<String>("")
    var originalTransactionId: StateFlow<String> = _originalTransactionId.asStateFlow()

    private val _deviceType = MutableStateFlow<String>("")
    var deviceType: StateFlow<String> = _deviceType.asStateFlow()

    private val _osVersion = MutableStateFlow<String>("")
    var osVersion: StateFlow<String> = _osVersion.asStateFlow()

    private val _currentVersionApp = MutableStateFlow<String>("")
    var currentVersionApp: StateFlow<String> = _currentVersionApp.asStateFlow()

    private val _newVersionApp = MutableStateFlow<String>("")
    var newVersionApp: StateFlow<String> = _newVersionApp.asStateFlow()

    private val _historyTransaction = MutableStateFlow<String>("")
    var historyTransaction: StateFlow<String> = _historyTransaction.asStateFlow()

    private val _isShowLog = MutableStateFlow<Boolean>(false)
    var isHideLog: StateFlow<Boolean> = _isShowLog.asStateFlow()

    // 0 : all prod,
    // 1 : dev Apiary,
    // 2 : dev Production,
    // 3 : all cloud
    private val _endPointType = MutableStateFlow<Int>(0)
    var endPointType: StateFlow<Int> = _endPointType.asStateFlow()

    private val _skuOneMonthData = MutableStateFlow<String>("")
    var skuOneMonthData: StateFlow<String> = _skuOneMonthData.asStateFlow()

    private val _skuOneYearData = MutableStateFlow<String>("")
    var skuOneYearData: StateFlow<String> = _skuOneYearData.asStateFlow()

    private val _subProductRemote = MutableStateFlow<String>("")
    var subProductRemote: StateFlow<String> = _subProductRemote.asStateFlow()

    private val _subProductDebug = MutableStateFlow<String>("")
    var subProductDebug: StateFlow<String> = _subProductDebug.asStateFlow()

    private val _subVerificationResultTransaction = MutableStateFlow<String>("")
    var subVerificationResultTransaction: StateFlow<String> =
        _subVerificationResultTransaction.asStateFlow()

    private val _subEntitlement = MutableStateFlow<String>("")
    var subEntitlement: StateFlow<String> = _subEntitlement.asStateFlow()

    private val _subTransaction = MutableStateFlow<String>("")
    var subTransaction: StateFlow<String> = _subTransaction.asStateFlow()

    private val _storeKitLog = MutableStateFlow<String>("")
    var storeKitLog: StateFlow<String> = _storeKitLog.asStateFlow()

    private val _flavors = MutableStateFlow<String>("")
    var flavors: StateFlow<String> = _flavors.asStateFlow()

    suspend fun execute(): AboutAppModel {
        return AboutAppModel(
            "${currentVersionApp.value} - Kompas.id ${flavors.value}",
            Constants.aboutAppContent,
            "Informasi Perangkat",
            listOf(
                AppInfoModel("Email", guid.value),
                AppInfoModel("GUID", guid.value),
                AppInfoModel(
                    "Status User", "personalInfoResultState.userType.value.toString().lowercase()"
                ),
                AppInfoModel("Original ID Transaksi", originalTransactionId.value),
                AppInfoModel("ID Transaksi", transactionId.value),
                AppInfoModel("Tipe Perangkat", deviceType.value),
                AppInfoModel("Versi OS", osVersion.value),
                AppInfoModel("Versi App Saat Ini", currentVersionApp.value),
                AppInfoModel("Versi App Terbaru", newVersionApp.value),
                AppInfoModel("Riwayat Langganan", historyTransaction.value),
            ),

            )
    }
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

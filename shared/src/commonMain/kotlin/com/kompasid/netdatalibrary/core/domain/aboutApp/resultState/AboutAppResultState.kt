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
                AppInfoModel("GUID", guid.value),
                AppInfoModel(
                    "Status User", personalInfoResultState.userType.value.toString().lowercase()
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


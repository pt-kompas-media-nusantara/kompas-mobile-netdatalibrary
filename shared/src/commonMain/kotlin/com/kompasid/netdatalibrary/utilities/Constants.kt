package com.kompasid.netdatalibrary.utilities

import com.kompasid.netdatalibrary.core.domain.account.model.StateUserType
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.core.presentation.state.personalInfo.DeviceInfoResultState
import com.kompasid.netdatalibrary.core.presentation.state.personalInfo.PersonalInfoResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


object Constants {
    const val isUsinStatusCode = false
    const val HARDCODE_ACCESS_TOKEN =
        "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7ImVtYWlsIjoibnVyLmlyZmFuQGtvbXBhcy5jb20iLCJpZCI6Ijk0N2JhZjk2LTY3Y2QtNDVhNy1hYzMwLWU5MDA2NTFhOTZjMCIsInJ0IjoxNzYzNzAwNjQzLCJzY29wZSI6WyJyZWFkLWFydGljbGUiLCJyZWFkLXByb2ZpbGUiXX0sImV4cCI6MTczMjE2NTU0MywiaWF0IjoxNzMyMTY0NjQzLCJpc3MiOiJodHRwczovL3d3dy5rb21wYXMuaWQifQ.EmCwLJ7rEB4WqVeYDuofyUHj6kvRV2z0Zmwk0RrLjz6MRaGuKmgB9UvNrxVaSdP8p0fkQ5L6eK0d1k6Uk369lMuaNUhlZ0rrif13ulLHIKHzuRDg8l_cRFNPk28x9iN_R9Uz9WZpI9_S_y1zhefh_k2T58BIFWp2mri0ggwSQyM"
    const val HARDCODE_REFRESH_TOKEN =
        "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im51ci5pcmZhbkBrb21wYXMuY29tIiwiZXhwIjoxNzYzNzAwNjQzLCJpZCI6Ijk0N2JhZjk2LTY3Y2QtNDVhNy1hYzMwLWU5MDA2NTFhOTZjMCIsInN1YiI6MX0.oDQNT5I0yXn4Z4KOIKz-moabpMrH_nnHou6udOARmEReAV0ijVao8C1FKTz_7Jo0rxgTSa7ohH1S21jm3O1_EQikhMUWxMY4oR9r77zogT0BAcFdOcsazWARKt8OadYODLJn5J7l-vV_wSoGk2Yx-DIlE6RWoNQQJ0cgixsU9EA"

    const val aboutAppContent =
        "Kompas.id merupakan sebuah laman berita yang menyajikan informasi berkualitas dan dipadukan dengan layanan belanja di bawah naungan PT Kompas Media Nusantara. Kompas.id diharapkan dapat melengkapi kehadiran harian kompas di masyarakat."
}

data class AboutAppModel(
    val appVersion: String,
    val desc: String,
    val appInfoTitle: String,
    val appInfo: List<AppInfoModel>,
)

data class AppInfoModel(
    val key: String,
    val value: String,
)

//class AboutAppUseCase()
class AboutAppResultState(
    private val personalInfoResultState: PersonalInfoResultState,
    private val deviceInfoResultState: DeviceInfoResultState
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

    fun onAppear(): AboutAppModel {
        return AboutAppModel(
            "${currentVersionApp.value} - Kompas.id ${_flavors}",
            Constants.aboutAppContent,
            "Informasi Perangkat",
            listOf(
                AppInfoModel("GUID", guid.value),
                AppInfoModel(
                    "Status User", personalInfoResultState.userType.value.toString().lowercase()
                ),
                AppInfoModel("Original ID Transaksi", ""),
                AppInfoModel("ID Transaksi", ""),
                AppInfoModel("Tipe Perangkat", ""),
                AppInfoModel("Versi OS", ""),
                AppInfoModel("Versi App Saat Ini", ""),
                AppInfoModel("Versi App Terbaru", ""),
                AppInfoModel("Riwayat Langganan", ""),
            ),

//            AppInfoModel("GUID", guid.value),
//            AppInfoModel("GUID", guid.value),
//            AppInfoModel("GUID", guid.value),
//            AppInfoModel("GUID", guid.value),
//            AppInfoModel("GUID", guid.value),
//            AppInfoModel("GUID", guid.value),
        )
    }
}
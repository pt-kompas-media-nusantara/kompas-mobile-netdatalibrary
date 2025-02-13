package com.kompasid.netdatalibrary.netData.domain.MyAccountDomain

data class AccountModel(
    val menuIcon: String,
    val title: String,
    val desc: String,
    val navigation: AccountNavigationType
)

val manageAccountData = AccountModel(
    "icManageAccountData",
    "Kelola Akun",
    "Halaman detail akun Anda, meliputi: data diri, no ponsel, email & akun terhubung",
    AccountNavigationType.MANAGE_ACCOUNT
)
val subcriptionData = AccountModel(
    "icSubcriptionData",
    "Berlangganan",
    "Lihat tawaran berlangganan Kompas.id.",
    AccountNavigationType.SUBSCRIPTION
)
val bookmarkData = AccountModel(
    "icBookmarkData",
    "Baca Nanti",
    "Daftar artikel yang Anda simpan untuk dibaca nanti.",
    AccountNavigationType.BOOKMARK
)
val rewardData = AccountModel(
    "icRewardData",
    "Reward",
    "Lihat berbagai reward yang dapat Anda gunakan.",
    AccountNavigationType.REWARD
)
val settingData =
    AccountModel("icSettingData", "Pengaturan", "Atur fitur untuk akun Anda.", AccountNavigationType.SETTINGS)
val contactUsData = AccountModel(
    "icContactUsData",
    "Hubungi Kami",
    "Sampaikan kendala, kritik, dan saran Anda ke Tim Kompas.id.",
    AccountNavigationType.CONTACT_US
)
val qnaData = AccountModel(
    "icQnaData",
    "Tanya Jawab",
    "Temukan jawaban dari pertanyaan Anda seputar Kompas.id.",
    AccountNavigationType.QNA
)
val aboutAppData = AccountModel(
    "icAboutAppData",
    "Tentang Aplikasi",
    "Lihat informasi lengkap tentang aplikasi Kompas.id.",
    AccountNavigationType.ABOUT_APP
)
val aboutHarianKompasData = AccountModel(
    "icAboutHarianKompasData",
    "Tentang Harian Kompas",
    "Lihat profil lengkap Harian Kompas",
    AccountNavigationType.ABOUT_HARIAN_KOMPAS
)

/// Tentang Harian Kompas
val companyProfileData = AccountModel(
    "",
    "Profil Perusahaan",
    "",
    AccountNavigationType.COMPANY_PROFILE
)

val companyHistoryData = AccountModel(
    "",
    "Sejarah Perusahaan",
    "",
    AccountNavigationType.COMPANY_HISTORY
)

val aboutOrganizationData = AccountModel(
    "",
    "Tentang Organisasi",
    "",
    AccountNavigationType.ABOUT_ORGANIZATION
)

/// About App
val aboutAppSubMenuData = AccountModel(
    "",
    "Tentang Aplikasi",
    "",
    AccountNavigationType.ABOUT_APP_SUBMENU
)

val termsConditionsData = AccountModel(
    "",
    "Syarat dan Ketentuan",
    "",
    AccountNavigationType.TERMS_CONDITIONS
)

val cyberMediaGuidelinesData = AccountModel(
    "",
    "Pedoman media siber",
    "",
    AccountNavigationType.CYBER_MEDIA_GUIDELINES
)

/// Pengaturan
val themeData = AccountModel(
    "icThemeData",
    "Tema Aplikasi",
    "Atur tema gelap atau terang pada aplikasi.",
    AccountNavigationType.THEME
)
val changePasswordData = AccountModel(
    "icChangePasswordData",
    "Ubah Sandi",
    "Mengubah sandi Anda dengan yang baru.",
    AccountNavigationType.CHANGE_PASSWORD
)
val deleteDataData = AccountModel(
    "icDeleteDataData",
    "Hapus Data",
    "Hapus data aplikasi Kompas.id dari gawai Anda.",
    AccountNavigationType.DELETE_DATA
)
val deviceActivitiesData = AccountModel(
    "icDeviceActivitiesData",
    "Aktivitas Perangkat",
    "Daftar perangkat yang terhubung dengan akun Anda.",
    AccountNavigationType.DEVICE_ACTIVITIES
)
val deleteAccountData = AccountModel(
    "icDeleteAccountData",
    "Hapus Akun",
    "Ketahui cara menghapus akun dan data pribadi Anda.",
    AccountNavigationType.DELETE_ACCOUNT
)
val signOutData = AccountModel("icSignOutData", "Keluar", "", AccountNavigationType.SIGN_OUT)
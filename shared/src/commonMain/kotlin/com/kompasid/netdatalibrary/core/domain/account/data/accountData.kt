package com.kompasid.netdatalibrary.core.domain.account.data

import com.kompasid.netdatalibrary.core.domain.account.enums.AccountNavigationType
import com.kompasid.netdatalibrary.core.domain.account.model.AccountModel

val manageAccountData = AccountModel(
    "ic_manage_account",
    "Kelola Akun",
    "Halaman detail akun Anda, meliputi: data diri, no ponsel, email & akun terhubung",
    AccountNavigationType.MANAGE_ACCOUNT
)
val subcriptionData = AccountModel(
    "ic_subcription",
    "Berlangganan",
    "Lihat tawaran berlangganan Kompas.id.",
    AccountNavigationType.SUBSCRIPTION
)
val bookmarkData = AccountModel(
    "ic_bookmark",
    "Baca Nanti",
    "Daftar artikel yang Anda simpan untuk dibaca nanti.",
    AccountNavigationType.BOOKMARK
)
val rewardData = AccountModel(
    "ic_reward",
    "Reward",
    "Lihat berbagai reward yang dapat Anda gunakan.",
    AccountNavigationType.REWARD
)
val settingData =
    AccountModel("ic_setting", "Pengaturan", "Atur fitur untuk akun Anda.", AccountNavigationType.SETTINGS)
val contactUsData = AccountModel(
    "ic_contact_us",
    "Hubungi Kami",
    "Sampaikan kendala, kritik, dan saran Anda ke Tim Kompas.id.",
    AccountNavigationType.CONTACT_US
)
val qnaData = AccountModel(
    "ic_qna",
    "Tanya Jawab",
    "Temukan jawaban dari pertanyaan Anda seputar Kompas.id.",
    AccountNavigationType.QNA
)
val aboutAppData = AccountModel(
    "ic_about_app",
    "Tentang Aplikasi",
    "Lihat informasi lengkap tentang aplikasi Kompas.id.",
    AccountNavigationType.ABOUT_APP
)
val aboutHarianKompasData = AccountModel(
    "ic_about_harian_kompas",
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
    "ic_theme",
    "Tema Aplikasi",
    "Atur tema gelap atau terang pada aplikasi.",
    AccountNavigationType.THEME
)
val changePasswordData = AccountModel(
    "ic_change_password",
    "Ubah Sandi",
    "Mengubah sandi Anda dengan yang baru.",
    AccountNavigationType.CHANGE_PASSWORD
)
val deleteDataData = AccountModel(
    "ic_delete_data",
    "Hapus ",
    "Hapus data aplikasi Kompas.id dari gawai Anda.",
    AccountNavigationType.DELETE_DATA
)
val deviceActivitiesData = AccountModel(
    "ic_device_activities",
    "Aktivitas Perangkat",
    "Daftar perangkat yang terhubung dengan akun Anda.",
    AccountNavigationType.DEVICE_ACTIVITIES
)
val deleteAccountData = AccountModel(
    "ic_delete_account",
    "Hapus Akun",
    "Ketahui cara menghapus akun dan data pribadi Anda.",
    AccountNavigationType.DELETE_ACCOUNT
)
val signOutData = AccountModel("ic_sign_out", "Keluar", "Seluruh data akun dan preferensi tersimpan akan terhapus.", AccountNavigationType.SIGN_OUT)
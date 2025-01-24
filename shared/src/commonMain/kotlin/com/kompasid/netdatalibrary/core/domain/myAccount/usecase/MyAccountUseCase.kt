package com.kompasid.netdatalibrary.core.domain.myAccount.usecase


class MyAccountUseCase(
) {

    suspend fun suberAccountMenu(): List<AccountModel> {
        return listOf(
            manageAccountData,
            bookmarkData,
            rewardData,
            settingData,
            contactUsData,
            qnaData,
            aboutAppData,
            aboutHarianKompasData,
        )
    }


}

data class AccountModel(
    val menuIcon: String,
    val title: String,
    val desc: String,
    val navigation: AccountNavigationType
)

enum class AccountNavigationType(val type: String) {
    LOGIN("LOGIN"),
    REGISTER("REGISTER"),
    MANAGE_ACCOUNT("MANAGE_ACCOUNT"),
    SUBSCRIPTION("SUBSCRIPTION"),
    BOOKMARK("BOOKMARK"),
    REWARD("REWARD"),
    SETTINGS("SETTINGS"),
    CONTACT_US("CONTACT_US"),
    QNA("QNA"),
    ABOUT_APP("ABOUT_APP"),
    ABOUT_HARIAN_KOMPAS("ABOUT_HARIAN_KOMPAS"),
}

val manageAccountData = AccountModel("", "Kelola Akun", "Halaman detail akun Anda, meliputi: data diri, no ponsel, email & akun terhubung", AccountNavigationType.MANAGE_ACCOUNT)
val subcriptionData = AccountModel("", "Berlangganan", "Lihat tawaran berlangganan Kompas.id.", AccountNavigationType.SUBSCRIPTION)
val bookmarkData = AccountModel("", "Baca Nanti", "Daftar artikel yang Anda simpan untuk dibaca nanti.", AccountNavigationType.BOOKMARK)
val rewardData = AccountModel("", "Reward", "Lihat berbagai reward yang dapat Anda gunakan.", AccountNavigationType.REWARD)
val settingData = AccountModel("", "Pengaturan", "Atur fitur untuk akun Anda.", AccountNavigationType.SETTINGS)
val contactUsData = AccountModel("", "Hubungi Kami", "Sampaikan kendala, kritik, dan saran Anda ke Tim Kompas.id.", AccountNavigationType.CONTACT_US)
val qnaData = AccountModel("", "Tanya Jawab", "Temukan jawaban dari pertanyaan Anda seputar Kompas.id.", AccountNavigationType.QNA)
val aboutAppData = AccountModel("", "Tentang Aplikasi", "Lihat informasi lengkap tentang aplikasi Kompas.id.", AccountNavigationType.ABOUT_APP)
val aboutHarianKompasData = AccountModel("", "Tentang Harian Kompas", "Lihat profil lengkap Harian Kompas", AccountNavigationType.ABOUT_HARIAN_KOMPAS)


val themeData = AccountModel("", "Tema Aplikasi", "Atur tema gelap atau terang pada aplikasi.", AccountNavigationType.MANAGE_ACCOUNT)
val changePasswordData = AccountModel("", "Ubah Sandi", "Mengubah sandi Anda dengan yang baru.", AccountNavigationType.MANAGE_ACCOUNT)
val deleteDataData = AccountModel("", "Hapus Data", "Hapus data aplikasi Kompas.id dari gawai Anda.", AccountNavigationType.MANAGE_ACCOUNT)
val deviceActivitiesData = AccountModel("", "Aktivitas Perangkat", "Daftar perangkat yang terhubung dengan akun Anda.", AccountNavigationType.MANAGE_ACCOUNT)
val deleteAccountData = AccountModel("", "Hapus Akun", "Ketahui cara menghapus akun dan data pribadi Anda.", AccountNavigationType.MANAGE_ACCOUNT)
val signOutData = AccountModel("", "Keluar", "", AccountNavigationType.MANAGE_ACCOUNT)


//suspend fun suberRegonMenu(): List<AccountModel> {
//    return listOf(
//        settingData,
//        contactUsData,
//        qnaData,
//        aboutAppData,
//        aboutHarianKompasData,
//    )
//}
//
//suspend fun suberAnonMenu(): List<AccountModel> {
//    return listOf(
//        manageAccountData,
//        subcriptionData,
//        settingData,
//        contactUsData,
//        qnaData,
//        aboutAppData,
//        aboutHarianKompasData,
//    )
//}
//
//suspend fun settingsAccountMenu(): List<AccountModel> {
//    return listOf(
//        themeData,
//        changePasswordData,
//        deleteDataData,
//        deviceActivitiesData,
//        deleteAccountData,
//        signOutData,
//    )
//}
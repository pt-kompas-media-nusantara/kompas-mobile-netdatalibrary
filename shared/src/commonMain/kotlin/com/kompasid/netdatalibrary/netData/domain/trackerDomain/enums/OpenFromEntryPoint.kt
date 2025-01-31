package com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums

//enum class OpenFromEntryPoint(val description: String) {
//    Empty("Empty"),
//    HotTopicSection("Section Topik Hangat"),
//    UtamaSection("Section Berita Utama"),
//    TerbaruSection("Section Terbaru"),
//    TerpopulerSection("Section Terpopuler"),
//    FavpemSection("Section Favorit Pembaca"),
//    PilihankuSection("Section Pilihanku"),
//    RelatedArticleSection("Section Artikel Terkait"),
//    OtherArticleSection("Section Artikel Lainnya"),
//    ReadLaterSection("Section Baca Lainnya"),
//    LandingSearch("Search Page"),
//    SearchResult("Search Result Page"),
//    MyAccount("Akun Saya Page"),
//    SubscriptionStatus("Status Langganan Page"),
//    PromoPage("Promo Page"),
//    SettingPage("Pengaturan Page"),
//    HelpPage("Bantuan Page"),
//    AboutAppPage("Tentang Aplikasi Page"),
//    AllEditionEpaperPage("Semua Edisi Page"),
//    MyEpaperPage("Epaper Saya Page"),
//    DetailEpaperPage("Detail Epaper Page"),
//    SettingEpaperPage("Atur Epaper Page"),
//    AllBooksPage("Semua Buku Page"),
//    MyBooksPage("Buku Saya Page"),
//    DetailBookPage("Detail Buku Page"),
//    SettingBookPage("Atur Buku Page"),
//    ExternalLink("External Link"),
//    AudioArticlePage("Audio Artikel Page"),
//    ContactUsPage("Hubungi Kami Page"),
//    QuestionAnswerPage("Tanya Jawab Page"),
//    AboutKompasDailyPage("Tentang Harian Kompas Page"),
//    FullPlayerAudioArticlePage("Full Player Audio Artikel"),
//    Onboarding("Onboarding Page"),
//    ReadAlso("Baca Juga"),
//    TranslatorMark("Translator Mark"),
//    NotificationPage("Notifikasi Page"),
//    NotificationInfo("Notification Info"),
//    NotificationContent("Notification Content"),
//    KompasBrief("Section Kompas Brief");
//
//    companion object {
//        fun fromString(value: String): OpenFromEntryPoint {
//            return entries.find { it.name.equals(value, ignoreCase = true) } ?: Empty
//        }
//    }
//}


sealed class OpenFromEntryPoint(val description: String) {

    // Enum-like objects
    object Empty : OpenFromEntryPoint("Empty")
    object HotTopicSection : OpenFromEntryPoint("Section Topik Hangat")
    object UtamaSection : OpenFromEntryPoint("Section Berita Utama")
    object TerbaruSection : OpenFromEntryPoint("Section Terbaru")
    object TerpopulerSection : OpenFromEntryPoint("Section Terpopuler")
    object FavpemSection : OpenFromEntryPoint("Section Favorit Pembaca")
    object PilihankuSection : OpenFromEntryPoint("Section Pilihanku")
    object RelatedArticleSection : OpenFromEntryPoint("Section Artikel Terkait")
    object OtherArticleSection : OpenFromEntryPoint("Section Artikel Lainnya")
    object ReadLaterSection : OpenFromEntryPoint("Section Baca Lainnya")
    object LandingSearch : OpenFromEntryPoint("Search Page")
    object SearchResult : OpenFromEntryPoint("Search Result Page")
    object MyAccount : OpenFromEntryPoint("Akun Saya Page")
    object SubscriptionStatus : OpenFromEntryPoint("Status Langganan Page")
    object PromoPage : OpenFromEntryPoint("Promo Page")
    object SettingPage : OpenFromEntryPoint("Pengaturan Page")
    object HelpPage : OpenFromEntryPoint("Bantuan Page")
    object AboutAppPage : OpenFromEntryPoint("Tentang Aplikasi Page")
    object AllEditionEpaperPage : OpenFromEntryPoint("Semua Edisi Page")
    object MyEpaperPage : OpenFromEntryPoint("Epaper Saya Page")
    object DetailEpaperPage : OpenFromEntryPoint("Detail Epaper Page")
    object SettingEpaperPage : OpenFromEntryPoint("Atur Epaper Page")
    object AllBooksPage : OpenFromEntryPoint("Semua Buku Page")
    object MyBooksPage : OpenFromEntryPoint("Buku Saya Page")
    object DetailBookPage : OpenFromEntryPoint("Detail Buku Page")
    object SettingBookPage : OpenFromEntryPoint("Atur Buku Page")
    object ExternalLink : OpenFromEntryPoint("External Link")
    object AudioArticlePage : OpenFromEntryPoint("Audio Artikel Page")
    object ContactUsPage : OpenFromEntryPoint("Hubungi Kami Page")
    object QuestionAnswerPage : OpenFromEntryPoint("Tanya Jawab Page")
    object AboutKompasDailyPage : OpenFromEntryPoint("Tentang Harian Kompas Page")
    object FullPlayerAudioArticlePage : OpenFromEntryPoint("Full Player Audio Artikel")
    object Onboarding : OpenFromEntryPoint("Onboarding Page")
    object ReadAlso : OpenFromEntryPoint("Baca Juga")
    object TranslatorMark : OpenFromEntryPoint("Translator Mark")
    object NotificationPage : OpenFromEntryPoint("Notifikasi Page")
    object NotificationInfo : OpenFromEntryPoint("Notification Info")
    object NotificationContent : OpenFromEntryPoint("Notification Content")
    object KompasBrief : OpenFromEntryPoint("Section Kompas Brief")

    // Special cases with dynamic parameters
    data class RubricSection(val name: String) : OpenFromEntryPoint("Section $name")
    data class RubricIndex(val name: String) : OpenFromEntryPoint("$name Page")

    companion object {
        fun fromString(value: String): OpenFromEntryPoint? {
            return when (value.uppercase()) {
                "" -> Empty
                "HOT_TOPIC_SECTION" -> HotTopicSection
                "UTAMA_SECTION" -> UtamaSection
                "TERBARU_SECTION" -> TerbaruSection
                "TERPOPULER_SECTION" -> TerpopulerSection
                "FAVPEM_SECTION" -> FavpemSection
                "PILIHANKU_SECTION" -> PilihankuSection
                "RELATED_ARTICLE_SECTION" -> RelatedArticleSection
                "OTHER_ARTICLE_SECTION" -> OtherArticleSection
                "READ_LATER_SECTION" -> ReadLaterSection
                "LANDING_SEARCH" -> LandingSearch
                "SEARCH_RESULT" -> SearchResult
                "MY_ACCOUNT" -> MyAccount
                "SUBSCRIPTION_STATUS" -> SubscriptionStatus
                "PROMO_PAGE" -> PromoPage
                "SETTING_PAGE" -> SettingPage
                "HELP_PAGE" -> HelpPage
                "ABOUT_APP_PAGE" -> AboutAppPage
                "ALL_EDITION_EPAPER_PAGE" -> AllEditionEpaperPage
                "MY_EPAPER_PAGE" -> MyEpaperPage
                "DETAIL_EPAPER_PAGE" -> DetailEpaperPage
                "SETTING_EPAPER_PAGE" -> SettingEpaperPage
                "ALL_BOOKS_PAGE" -> AllBooksPage
                "MY_BOOKS_PAGE" -> MyBooksPage
                "DETAIL_BOOK_PAGE" -> DetailBookPage
                "SETTING_BOOK_PAGE" -> SettingBookPage
                "EXTERNAL_LINK" -> ExternalLink
                "AUDIO_ARTICLE_PAGE" -> AudioArticlePage
                "CONTACT_US_PAGE" -> ContactUsPage
                "QUESTION_ANSWER_PAGE" -> QuestionAnswerPage
                "ABOUT_KOMPAS_DAILY_PAGE" -> AboutKompasDailyPage
                "FULL_PLAYER_AUDIO_ARTICLE_PAGE" -> FullPlayerAudioArticlePage
                "ONBOARDING" -> Onboarding
                "READ_ALSO" -> ReadAlso
                "TRANSLATOR_MARK" -> TranslatorMark
                "NOTIFICATION_PAGE" -> NotificationPage
                "NOTIFICATION_INFO" -> NotificationInfo
                "NOTIFICATION_CONTENT" -> NotificationContent
                "KOMPAS_BRIEF" -> KompasBrief
                else -> null
            }
        }
    }
}
//
//fun getOpenFromEntryPoint(page: OpenFromEntryPoint): String {
//    return page.description
//}

//// Contoh penggunaan dengan objek statis
//val page1 = OpenFromEntryPoint.HotTopicSection
//println(getOpenFromEntryPoint(page1)) // Output: Section Topik Hangat
//
//// Contoh penggunaan dengan parameter dinamis
//val page2 = OpenFromEntryPoint.RubricSection("Politik")
//println(getOpenFromEntryPoint(page2)) // Output: Section Politik
//
//// Contoh konversi dari string
//val pageFromString = OpenFromEntryPoint.fromString("TERBARU_SECTION")
//println(getOpenFromEntryPoint(pageFromString!!)) // Output: Section Terbaru

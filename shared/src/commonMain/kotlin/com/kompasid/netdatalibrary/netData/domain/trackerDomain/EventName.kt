package com.kompasid.netdatalibrary.netData.domain.trackerDomain

//enum class EventName(val value: String) {
//    EXAMPLE("EXAMPLE"),
//    SIGN_UP_STARTED("sign_up_started")
//}



// TODO: Wahyu - Kalau bisa penggunaan enum ini dikurangin untuk list data yang banyak mas,
//  karena banyak boilerplate yang sebenernya gadipake untuk case ini,
//  alternatif bisa pake constant atau sealed class

//object TrackerEventName {
//    const val EXAMPLE = "example"
//}
//
//sealed class TrackerName(val name: String){
//    data object LoginSuccess : TrackerName("login_success")
//    data object Example : TrackerName("example")
//
//    data class CustomEvent(val customName: String) : TrackerName(customName)
//}

enum class EventName(val value: String) {
    EXAMPLE("EXAMPLE"),
    SIGN_UP_STARTED("sign_up_started"),
//    ACCOUNT_DATA_SUBMITTED("account_data_submitted"),
//    SIGN_UP_COMPLETED("sign_up_completed"),
//    SIGN_IN_COMPLETED("sign_in_completed"),
//    SIGN_OUT_COMPLETED("sign_out_completed"),
    PAGE_VIEWED("page_viewed"),
//    CONTENT_BOOKMARKED("content_bookmarked"),
//    CONTENT_SHARED("content_shared"),
//    COPY_LINK_CLICKED("copy_link_clicked"),
//    WHATSAPP_CLICKED("whatsapp_clicked"),
//    INSTAGRAM_STORIES_CLICKED("instagram_stories_clicked"),
//    SHARE_VIA_CLICKED("share_via_clicked"),
//    SEARCH_STARTED("search_started"),
//    SEARCH_RESULT_FILTERED("search_result_filtered"),
//    SEARCH_RESULT_CLICKED("search_result_clicked"),
//    AUDIO_CLICKED("audio_clicked"),
//    AUDIO_PLAYED("audio_played"),
//    AUDIO_STOPPED("audio_stopped"),
//    STORY_MODE_CLICKED("story_mode_clicked"),
//    PAGE_SLIDED("page_slided"),
//    EPAPER_DOWNLOAD_STARTED("epaper_download_started"),
//    EPAPER_PAGE_VIEWED("epaper_page_viewed"),
//    EPAPER_SEARCHED("epaper_searched"),
//    EPAPER_CAPTURED("epaper_captured"),
//    EBOOK_DOWNLOAD_STARTED("ebook_download_started"),
//    EBOOK_PAGE_VIEWED("ebook_page_viewed"),
//    EBOOK_CAPTURED("ebook_captured"),
//    EBOOK_SEARCH("ebook_search"),
//    EBOOK_RESULT_CLICKED("ebook_result_clicked"),
//    EBOOK_SORTING("ebook_sorting"),
//    PAYWALL_VIEWED("paywall_viewed"),
//    SUBSCRIPTION_PACKAGE_VIEWED("subscription_package_viewed"),
//    SUBSCRIBE_BUTTON_CLICKED("subscribe_button_clicked"),
//    SUBSCRIBE_CONFIRMATION_VIEWED("subscribe_confirmation_viewed"),
//    PAYMENT_STARTED("payment_started"),
//    PAY_BUTTON_CLICKED("pay_button_clicked"),
//    PAYMENT_CONFIRMED("payment_confirmed"),
//    CONTENT_CONSUMED("content_consumed"),
//    MRW_VIEWED("mrw_viewed"),
//    MRW_CLICKED("mrw_clicked"),
//    MRW_CLOSED("mrw_closed"),
//    APP_THEME_APPLIED("app_theme_applied"),
//    EPAPER_MODE_ARTIKEL_VIEWED("epaper_mode_artikel_viewed"),
//    TOPIK_HANGAT_CLICKED("topik_hangat_clicked"),
//    DELETE_ACCOUNT_CLICKED("delete_account_clicked"),
//    DELETE_ACCOUNT_STARTED("delete_account_started"),
//    PILIHANKU_OFFERING_VIEWED("pilihanku_offering_viewed"),
//    PILIHANKU_CONFIRMED("pilihanku_confirmed"),
//    COMMENT_OPENED("comment_opened"),
//    COMMENT_SUBMITTED("comment_submitted"),
//    READING_COMPLETED("reading_completed"),
//    SUBSCRIBER_ONBOARDING_STARTED("subscriber_onboarding_started"),
//    SUBSCRIBER_ONBOARDING_VIEWED("subscriber_onboarding_viewed"),
//    PILIHANKU_SKIPPED("pilihanku_skipped"),
//    SUBSCRIBER_ONBOARDING_COMPLETED("subscriber_onboarding_completed"),
//    SUBSCRIBER_ONBOARDING_CLICKED("subscriber_onboarding_clicked"),
//    CHECKOUT_STARTED("checkout_started"),
//    CHECKOUT_COMPLETED("checkout_completed"),
//    COUPON_VIEWED("coupon_viewed"),
//    COUPON_APPLIED("coupon_applied"),
//    OTHER_PACKAGES_CLICKED("other_packages_clicked"),
//    CUSTOMER_SERVICE_CLICKED("customer_service_clicked"),
//    RECO_CONTENT_CLICKED("reco_content_clicked"),
//    RECO_CONTENT_VIEWED("reco_content_viewed"),
//    REWARD_CLICKED("reward_clicked"),
//    SAVED_FULL_NAME("saved_full_name"),
//    SAVED_GENDER("saved_gender"),
//    SAVED_BIRTHDATE("saved_birthdate"),
//    SAVED_DOMICILE("saved_domicile"),
//    REPOLA_CARD_VIEWED("repola_card_viewed"),
//    REPOLA_CARD_CLICKED("repola_card_clicked"),
//    REPOLA_BANNER_VIEWED("repola_banner_viewed"),
//    REPOLA_BANNER_CLICKED("repola_banner_clicked"),
//    BACA_DI_APP_CLICKED("baca_di_app_clicked"),
//    IFRAME_WIDGET_VIEWED("iframe_widget_viewed"),
//    COACHMARK_VIEWED("coachmark_viewed"),
//    COACHMARK_CLOSED("coachmark_closed"),
//    COACHMARK_FINISHED("coachmark_finished"),
//    VIDEO_PLAYED("video_played"),
//    NOTIFICATION_CLICKED("notification_clicked"),
//    NOTIFICATION_OPENED("notification_opened"),
//    RUBRIC_CLICKED("rubric_clicked"),
//    PILIHANKU_OFFERING_CLICKED("pilihanku_offering_clicked"),
//    FRONT_PAGE_SET("front_page_set"),
//    HOMEPAGE_ONBOARDING_VIEWED("homepage_onboarding_viewed"),
//    HOMEPAGE_ONBOARDING_FINISHED("homepage_onboarding_finished"),
//    SECTION_VIEWED("section_viewed"),
//    SECTION_CLICKED("section_clicked")
}


package com.kompasid.netdatalibrary.netData.domain.trackerDomain

import com.kompasid.netdatalibrary.helper.UserDataHelper
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.AuthenticationEntryPoint
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.OpenFromEntryPoint
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.AboutKompasDailyModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.ExampleModel
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.SignUpStartedModel

class TrackerUseCase(
    private var trackerManager: TrackerManager,
    private var userDataHelper: UserDataHelper,
) {

    suspend fun pageViewed(openFromEntryPoint: OpenFromEntryPoint) {
        trackerManager.post(
            EventName.PAGE_VIEWED,
            pageViewedMapping(openFromEntryPoint),
            userDataHelper.userDataTracker()
        )
    }

    private fun pageViewedMapping(openFromEntryPoint: OpenFromEntryPoint): Any {
        return when (openFromEntryPoint) {
            is OpenFromEntryPoint.AboutAppPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.AboutKompasDailyPage -> AboutKompasDailyModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.AllBooksPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.AllEditionEpaperPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.AudioArticlePage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.ContactUsPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.DetailBookPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.DetailEpaperPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.ExternalLink -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.FavpemSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.FullPlayerAudioArticlePage -> ExampleModel(
                openFromEntryPoint.description
            )

            is OpenFromEntryPoint.HelpPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.HotTopicSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.KompasBrief -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.LandingSearch -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.MyAccount -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.MyBooksPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.MyEpaperPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.NotificationContent -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.NotificationInfo -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.NotificationPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.Onboarding -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.OtherArticleSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.PilihankuSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.PromoPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.QuestionAnswerPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.ReadAlso -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.ReadLaterSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.RelatedArticleSection -> ExampleModel(
                openFromEntryPoint.description
            )

            is OpenFromEntryPoint.RubricIndex -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.RubricSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.SearchResult -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.SettingBookPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.SettingEpaperPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.SettingPage -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.SubscriptionStatus -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.TerbaruSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.TerpopulerSection -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.TranslatorMark -> ExampleModel(openFromEntryPoint.description)
            is OpenFromEntryPoint.UtamaSection -> ExampleModel(openFromEntryPoint.description)
        }
    }
}

//page_viewed	Saat user membuka halaman Tentang Harian Kompas		All	page_title	Page Title	Title of the page	String	String	String	String	Tentang Harian Kompas Page
//All	user_type	User Type	Type of user based on their subscription	String	String	String	String	G,R,S
//All	subscription_status	Subscription Status	Status of their subscription	String	String	String	String	IA, AC, EX, GP (default "")
//All	metered_wall_type	Metered Wall Type	The type of Metered Wall	String	String	String	String	MRW, MP, HP (default "")
//All	metered_wall_balance	Metered Wall Balance	The balance of their metered wall	Number	Number	Number	Number	5,4,3,2,1,0 (default 0)
//All	page_domain	Page Domain	Page Domain	String	String	String	String	Kompas.id
//All	subscription_package	Kompas.id Subscription Product	The name of Kompas.id subscription package	String	String	String	String	cash - app - kdd 1 - reguler (default "")
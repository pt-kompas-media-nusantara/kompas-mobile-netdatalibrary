package com.kompasid.netdatalibrary.di

import com.kompasid.netdatalibrary.base.di.base.sharedKoinModules
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerManager
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerUseCase
import com.kompasid.netdatalibrary.core.domain.account.usecase.AccountUseCase
import com.kompasid.netdatalibrary.core.domain.articleList.usecase.ArticleListUseCase
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.domain.forceUpdate.useCase.ForceUpdateUseCase
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.GeneralContentUseCase
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.domain.myRubriks.useCase.MyRubriksUseCase
import com.kompasid.netdatalibrary.core.domain.osRecomendation.useCase.OSRecomendationUseCase
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.domain.token.usecase.TokenUseCase
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin


fun initKoin() {

    val modules = sharedKoinModules

    startKoin {
        modules(modules)
    }
}

class KoinInjector : KoinComponent {
    val accountUseCase: AccountUseCase by inject()
    val articleListUseCase: ArticleListUseCase by inject()
    val authUseCase: AuthUseCase by inject()
    val forceUpdateUseCase: ForceUpdateUseCase by inject()
    val generalContentUseCase: GeneralContentUseCase by inject()
    val launchAppUseCase: LaunchAppUseCase by inject()
    val myRubriksUseCase: MyRubriksUseCase by inject()
    val osRecomendationUseCase: OSRecomendationUseCase by inject()
    val personalInfoUseCase: PersonalInfoUseCase by inject()
    val tokenUseCase: TokenUseCase by inject()
    val settingsHelper: SettingsHelper by inject()
    val supportSettingsHelper: SupportSettingsHelper by inject()


}
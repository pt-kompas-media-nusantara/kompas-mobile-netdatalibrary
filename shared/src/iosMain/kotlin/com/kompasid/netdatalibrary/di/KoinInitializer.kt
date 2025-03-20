package com.kompasid.netdatalibrary.di

import com.kompasid.netdatalibrary.base.di.base.sharedKoinModules
import com.kompasid.netdatalibrary.core.data.myRubriks.resultState.MyRubriksState
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerManager
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerUseCase
import com.kompasid.netdatalibrary.core.domain.account.usecase.AccountUseCase
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.domain.myRubriks.useCase.MyRubriksUseCase

import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.domain.settings.SettingsUseCase
import com.kompasid.netdatalibrary.core.domain.updateOS.useCase.UpdateOSUseCase
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
    val settingsHelper: SettingsHelper by inject()

    val authUseCase: AuthUseCase by inject()

    val personalInfoUseCase: PersonalInfoUseCase by inject()


    val launchAppUseCase: LaunchAppUseCase by inject()
    val updateOSUseCase: UpdateOSUseCase by inject()

    val accountUseCase: AccountUseCase by inject()
    val trackerUseCase: TrackerUseCase by inject()


    val myRubriksState: MyRubriksState by inject()
    val myRubriksUseCase: MyRubriksUseCase by inject()

    val trackerManager: TrackerManager by inject()

    val settingsUseCase: SettingsUseCase by inject()


}
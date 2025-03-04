package com.kompasid.netdatalibrary.di

import com.kompasid.netdatalibrary.base.di.base.sharedKoinModules
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerManager
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerUseCase
import com.kompasid.netdatalibrary.core.domain.account.usecase.AccountUseCase
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoState
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
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
    val authUseCase: AuthUseCase by inject()
    val accountUseCase: AccountUseCase by inject()
    val trackerUseCase: TrackerUseCase by inject()

    val trackerManager: TrackerManager by inject()

    val personalInfoState: PersonalInfoState by inject()
    val personalInfoUseCase: PersonalInfoUseCase by inject()
}
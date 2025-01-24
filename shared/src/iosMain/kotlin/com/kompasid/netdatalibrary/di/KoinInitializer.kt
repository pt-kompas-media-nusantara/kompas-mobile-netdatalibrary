package com.kompasid.netdatalibrary.di

import com.kompasid.netdatalibrary.base.di.base.sharedKoinModules
import com.kompasid.netdatalibrary.core.domain.myAccount.usecase.MyAccountUseCase
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.presentation.authPresentation.AuthVM
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

    val authVM: AuthVM by inject()
    val authUseCase: AuthUseCase by inject()
    val myAccountUseCase: MyAccountUseCase by inject()
    val settingsUseCase: SettingsUseCase by inject()
}
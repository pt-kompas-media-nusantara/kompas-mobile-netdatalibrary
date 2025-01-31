package com.kompasid.netdatalibrary.di

import com.kompasid.netdatalibrary.base.di.base.sharedKoinModules
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.MyAccountUseCase
import com.kompasid.netdatalibrary.netData.domain.SettingsDomain.SettingsUseCase
import com.kompasid.netdatalibrary.netData.domain.authDomain.AuthUseCase
import com.kompasid.netdatalibrary.netData.presentation.AuthVM
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerManager
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerUseCase
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
    val trackerUseCase: TrackerUseCase by inject()
    val trackerManager: TrackerManager by inject()

}
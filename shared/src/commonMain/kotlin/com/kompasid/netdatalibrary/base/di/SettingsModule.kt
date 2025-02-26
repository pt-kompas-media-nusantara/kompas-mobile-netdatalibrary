package com.kompasid.netdatalibrary.base.di

import com.kompasid.netdatalibrary.helper.UserDataHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val settingsModule = module {

    singleOf(::Settings) { bind<Settings>() }
    single<SuspendSettings> { get<Settings>().toSuspendSettings() }
    single<ObservableSettings> { get<Settings>() as ObservableSettings }
    single<FlowSettings> { get<ObservableSettings>().toFlowSettings() }

    singleOf(::SettingsHelper) { bind<SettingsHelper>() }
}



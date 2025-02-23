package com.kompasid.netdatalibrary.base.di

import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val settingsModule = module {

    singleOf(::Settings) { bind<Settings>() }
    // Buat instance ObservableSettings dari Settings
    single<ObservableSettings> { get<Settings>() as ObservableSettings }
    singleOf(::SettingsHelper) { bind<SettingsHelper>() }
}
package com.kompasid.netdatalibrary.base.di

import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val settingsModule = module {
    singleOf(::Settings) { bind<Settings>() }
    singleOf(::SettingsHelper) { bind<SettingsHelper>() }
}
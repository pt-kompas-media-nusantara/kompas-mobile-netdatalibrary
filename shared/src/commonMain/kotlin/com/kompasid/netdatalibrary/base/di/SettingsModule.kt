package com.kompasid.netdatalibrary.base.di

import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import org.koin.dsl.module


val settingsModule = module {
    single<SettingsDataSource> { SettingsDataSource() }
}
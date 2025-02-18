package com.kompasid.netdatalibrary.base.di.base

import com.kompasid.netdatalibrary.base.di.netDataModule
import com.kompasid.netdatalibrary.base.di.networkModule
import com.kompasid.netdatalibrary.base.di.settingsModule

val sharedKoinModules = listOf(
    networkModule,
    netDataModule,
    settingsModule
)
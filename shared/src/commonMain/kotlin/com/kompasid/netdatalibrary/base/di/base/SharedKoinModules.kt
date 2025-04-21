package com.kompasid.netdatalibrary.base.di.base

import com.kompasid.netdatalibrary.base.di.accountModule
import com.kompasid.netdatalibrary.base.di.authModule
import com.kompasid.netdatalibrary.base.di.forceUpdateModule
import com.kompasid.netdatalibrary.base.di.helperModule
import com.kompasid.netdatalibrary.base.di.launchAppModule
import com.kompasid.netdatalibrary.base.di.myRubriksModule
import com.kompasid.netdatalibrary.base.di.netDataModule
import com.kompasid.netdatalibrary.base.di.networkModule
import com.kompasid.netdatalibrary.base.di.osRecommendationModule
import com.kompasid.netdatalibrary.base.di.personalInfoModule
import com.kompasid.netdatalibrary.base.di.settingsModule
import com.kompasid.netdatalibrary.base.di.updateContentModule
import com.kompasid.netdatalibrary.base.di.updateTokenModule

val sharedKoinModules = listOf(
    networkModule,
    netDataModule,
    settingsModule,
    helperModule,

    updateTokenModule,
    authModule,
    personalInfoModule,
    launchAppModule,
    updateContentModule,
    accountModule,
    myRubriksModule,
    osRecommendationModule,
    forceUpdateModule,
)


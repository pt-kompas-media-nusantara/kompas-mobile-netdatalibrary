package com.kompasid.netdatalibrary.android

import android.app.Application
import com.kompasid.netdatalibrary.android.di.viewModelsModule
import com.kompasid.netdatalibrary.base.di.base.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KompasIdApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules =
            sharedKoinModules + viewModelsModule

        startKoin {
            androidContext(this@KompasIdApp)
            modules(modules)
        }
    }
}
package com.kompasid.netdatalibrary.android

import android.app.Application
import com.kompasid.netdatalibrary.LoggerWrapper
import com.kompasid.netdatalibrary.Platform
import com.kompasid.netdatalibrary.android.di.viewModelsModule
import com.kompasid.netdatalibrary.base.di.base.sharedKoinModules
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KompasIdApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTracker()
        initLogger()
    }

    private fun initLogger() {
        LoggerWrapper().initLogger()
    }

    private fun initTracker() {
        TrackerManager.register { eventName, eventProperty ->
            println("Kelas B menerima: $eventName $eventProperty")
        }
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

// catatan :
// https://insert-koin.io/docs/reference/koin-core/dsl#application-dsl
//ini belum kita pakai sepenuhnya, coba cari tahu lebih lanjut
//logger( ) - describe what level and Logger implementation to use (by default use the EmptyLogger)
//modules( ) - set a list of Koin modules to load in the container (list or vararg list)
//properties() - load HashMap properties into Koin container
//fileProperties( ) - load properties from given file into Koin container
//environmentProperties( ) - load properties from OS environment into Koin container
//createEagerInstances() - create eager instances (Single definitions marked as createdAtStart)


// catatan :
//sepertinya ada beberapa arti yang belum di ketahui karna di dokumentasi tidak di berikan contoh
//https://insert-koin.io/docs/reference/koin-core/dsl#module-dsl
//module { // module content } - create a Koin Module
//To describe your content in a module, you can use the following functions:
//
//factory { //definition } - provide a factory bean definition
//single { //definition } - provide a singleton bean definition (also aliased as bean)
//get() - resolve a component dependency (also can use name, scope or parameters)
//bind() - add type to bind for given bean definition
//binds() - add types array for given bean definition
//scope { // scope group } - define a logical group for scoped definition
//scoped { //definition }- provide a bean definition that will exist only in a scope
//
//Within this option lambda, you can specify the following options:
//
//named("a_qualifier") - give a String qualifier to the definition
//named<MyType>() - give a Type qualifier to the definition
//bind<MyInterface>() - add type to bind for given bean definition
//binds(arrayOf(...)) - add types array for given bean definition
//createdAtStart() - create single instance at Koin start
package com.kompasid.netdatalibrary.android.di

import com.kompasid.netdatalibrary.core.presentation.launchApp.stateState.LaunchAppVM
import com.kompasid.netdatalibrary.core.presentation.AccountVM
import com.kompasid.netdatalibrary.core.presentation.auth.resultState.AuthVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.coroutineNoArgModuleSettings.CoroutineNoArgModuleSettingsVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.listenerNoArgModuleSettings.ListenerExampleNoArgModuleSettingsVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.noArgModuleSettings.ExampleNoArgModuleSettingsVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.serializationNoArgModuleSettings.SerializationNoArgModuleSettingsVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
//    viewModel { CoroutineNoArgModuleSettingsVM(get(), get()) }
    viewModelOf(::AccountVM)
    viewModelOf(::LaunchAppVM)
    viewModelOf(::AuthVM)
    viewModelOf(::ExampleNoArgModuleSettingsVM)
    viewModelOf(::ListenerExampleNoArgModuleSettingsVM)
    viewModelOf(::SerializationNoArgModuleSettingsVM)
    viewModelOf(::CoroutineNoArgModuleSettingsVM)
}


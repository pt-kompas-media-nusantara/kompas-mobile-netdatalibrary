package com.kompasid.netdatalibrary.android.di

import com.kompasid.netdatalibrary.core.presentation.launchApp.stateState.LaunchAppVM
import com.kompasid.netdatalibrary.core.presentation.AccountVM
import com.kompasid.netdatalibrary.core.presentation.auth.resultState.AuthVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
//    viewModel { AuthVM(get(), get()) }
    viewModelOf(::AccountVM)
    viewModelOf(::LaunchAppVM)
    viewModelOf(::AuthVM)
}


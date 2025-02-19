package com.kompasid.netdatalibrary.android.di

import com.kompasid.netdatalibrary.core.presentation.AccountVM
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
//    viewModel { AccountVM(get(), get()) }
    viewModelOf(::AccountVM)
}


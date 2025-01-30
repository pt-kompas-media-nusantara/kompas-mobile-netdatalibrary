package com.kompasid.netdatalibrary.android.di

import com.kompasid.netdatalibrary.netData.presentation.AuthVM
import com.kompasid.netdatalibrary.netData.presentation.MyAccountVM
import com.kompasid.netdatalibrary.netData.presentation.AppIconVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::AuthVM)
    viewModelOf(::AppIconVM)
    viewModelOf(::MyAccountVM)

}
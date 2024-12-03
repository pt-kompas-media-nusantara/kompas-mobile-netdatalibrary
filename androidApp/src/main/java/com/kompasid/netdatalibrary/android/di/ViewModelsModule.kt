package com.kompasid.netdatalibrary.android.di

import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import com.kompasid.netdatalibrary.netData.presentation.authPresentation.AuthVM
import com.kompasid.netdatalibrary.netData.presentation.appIconPresentation.AppIconVM
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::ArticlesVM)
    viewModelOf(::AuthVM)
    viewModelOf(::AppIconVM)

}
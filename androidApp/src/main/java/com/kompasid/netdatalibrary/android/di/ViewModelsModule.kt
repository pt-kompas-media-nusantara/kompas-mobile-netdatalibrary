package com.kompasid.netdatalibrary.android.di

import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import com.kompasid.netdatalibrary.netData.presentation.authPresentation.AuthVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

//@stateobject var viewModel = ArtileVM()
//@stateobject var viewModel = viewModelsModule

val viewModelsModule = module {
    viewModelOf(::ArticlesVM)
    viewModelOf(::AuthVM)

}
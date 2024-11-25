package com.kompasid.netdatalibrary.android.di

import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//@stateobject var viewModel = ArtileVM()
//@stateobject var viewModel = viewModelsModule

val viewModelsModule = module {
//    viewModelOf(::ArticlesVM)
//    viewModelOf(::AuthVM)

    viewModel {
        ArticlesVM(
            get(),
            get(),
        )
    }
//    viewModel {
//        AuthVM(
//            get()
//        )
//    }
}
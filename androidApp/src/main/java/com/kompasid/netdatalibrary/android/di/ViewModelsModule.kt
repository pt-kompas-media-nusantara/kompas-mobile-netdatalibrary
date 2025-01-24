package com.kompasid.netdatalibrary.android.di

// import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import com.kompasid.netdatalibrary.core.presentation.authPresentation.AuthVM
import com.kompasid.netdatalibrary.core.presentation.general_content_presentation.GeneralContentVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::AuthVM)
    viewModelOf(::GeneralContentVM)

}
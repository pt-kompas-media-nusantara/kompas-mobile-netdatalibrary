package com.kompasid.netdatalibrary.di

import com.kompasid.netdatalibrary.netData.presentation.authPresentation.AuthVM
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class KoinInjector : KoinComponent {

    val authVM: AuthVM by inject()
}
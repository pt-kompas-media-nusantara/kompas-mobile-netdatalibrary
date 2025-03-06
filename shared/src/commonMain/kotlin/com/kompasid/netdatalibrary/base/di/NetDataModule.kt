package com.kompasid.netdatalibrary.base.di

import com.kompasid.netdatalibrary.base.DecodeJWT
import com.kompasid.netdatalibrary.core.data.userDetail.dataSource.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.NetworkApiService.INetworkApiService
import com.kompasid.netdatalibrary.base.network.NetworkApiService.NetworkApiService
import com.kompasid.netdatalibrary.base.network.NetworkVM.INetworkVM
import com.kompasid.netdatalibrary.base.network.NetworkVM.NetworkVM
import com.kompasid.netdatalibrary.core.data.generalContent.network.GeneralContentApiService
import com.kompasid.netdatalibrary.core.data.generalContent.repository.GeneralContentRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.network.LoginEmailApiService
import com.kompasid.netdatalibrary.core.data.loginEmail.dataSource.LoginEmailDataSource
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginEmailRepository
import com.kompasid.netdatalibrary.core.data.loginGuest.network.LoginGuestApiService
import com.kompasid.netdatalibrary.core.data.loginGuest.dataSource.LoginGuestDataSource
import com.kompasid.netdatalibrary.core.data.loginGuest.repository.LoginGuestRepository
import com.kompasid.netdatalibrary.core.data.logout.network.LogoutApiService
import com.kompasid.netdatalibrary.core.data.logout.dataSource.LogoutDataSource
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.data.refreshToken.dataSource.RefreshTokenDataSource
import com.kompasid.netdatalibrary.core.data.refreshToken.network.RefreshTokenApiService
import com.kompasid.netdatalibrary.core.data.refreshToken.repository.RefreshTokenRepository
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.network.UserMembershipApiService
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource.UserMembershipDataSource
import com.kompasid.netdatalibrary.core.data.generalContent.network.IGeneralContentApiService
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.GeneralContentUseCase
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserMembershipsRepository
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IGeneralContentRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.resultState.LoginResultState
import com.kompasid.netdatalibrary.core.data.myRubriks.network.MyRubriksApiService
import com.kompasid.netdatalibrary.core.data.myRubriks.repository.MyRubriksRepository
import com.kompasid.netdatalibrary.core.data.myRubriks.resultState.MyRubriksState
import com.kompasid.netdatalibrary.core.data.userDetail.resultState.UserDetailResultState
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.resultState.UserHistoryMembershipResultState
import com.kompasid.netdatalibrary.core.domain.aboutApp.resultState.AboutAppResultState
import com.kompasid.netdatalibrary.core.domain.account.usecase.AccountUseCase
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.IGeneralContentUseCase
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.domain.myRubriks.useCase.MyRubriksUseCase
import com.kompasid.netdatalibrary.core.domain.personalInfo.resultState.PersonalInfoState
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import com.kompasid.netdatalibrary.core.domain.token.usecase.TokenUseCase
import com.kompasid.netdatalibrary.core.domain.updateOS.useCase.UpdateOSUseCase
import com.kompasid.netdatalibrary.core.presentation.AccountVM
import com.kompasid.netdatalibrary.core.presentation.auth.resultState.AuthVM
import com.kompasid.netdatalibrary.core.presentation.launchApp.stateState.LaunchAppVM
import com.kompasid.netdatalibrary.core.presentation.launchApp.stateState.LaunchAppResultState
import com.kompasid.netdatalibrary.helper.UserDataHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.example.coroutineNoArgModuleSettings.CoroutineNoArgModuleSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.example.listenerNoArgModuleSettings.ListenerNoArgModuleSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.example.noArgModuleSettings.ExampleNoArgModuleSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.example.serializationNoArgModuleSettings.SerializationNoArgModuleSettingsHelper
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    /// AuthInteractor
    singleOf(::LoginResultState) { bind<LoginResultState>() } // uji coba ui
    singleOf(::AuthVM) { bind<AuthVM>() }
    singleOf(::AuthUseCase) { bind<AuthUseCase>() }

    /// LoginGuest
    singleOf(::LoginGuestRepository) { bind<LoginGuestRepository>() }
    singleOf(::LoginGuestDataSource) { bind<LoginGuestDataSource>() }
    singleOf(::LoginGuestApiService) { bind<LoginGuestApiService>() }

    /// RefreshToken
    singleOf(::RefreshTokenRepository) { bind<RefreshTokenRepository>() }
    singleOf(::RefreshTokenDataSource) { bind<RefreshTokenDataSource>() }
    singleOf(::RefreshTokenApiService) { bind<RefreshTokenApiService>() }

    /// LoginEmail
    singleOf(::LoginEmailRepository) { bind<LoginEmailRepository>() }
    singleOf(::LoginEmailDataSource) { bind<LoginEmailDataSource>() }
    singleOf(::LoginEmailApiService) { bind<LoginEmailApiService>() }

    /// Logout
    singleOf(::LogoutRepository) { bind<LogoutRepository>() }
    singleOf(::LogoutDataSource) { bind<LogoutDataSource>() }
    singleOf(::LogoutApiService) { bind<LogoutApiService>() }
}

val personalInfoModule = module {
    singleOf(::PersonalInfoState) { bind<PersonalInfoState>() }
    singleOf(::PersonalInfoUseCase) { bind<PersonalInfoUseCase>() }

    /// UserDetail
    singleOf(::UserDetailResultState) { bind<UserDetailResultState>() } // uji coba ui
    singleOf(::UserDetailRepository) { bind<UserDetailRepository>() }
    singleOf(::UserDetailDataSource) { bind<UserDetailDataSource>() }
    singleOf(::UserDetailApiService) { bind<UserDetailApiService>() }

    /// User History Membership
    singleOf(::UserHistoryMembershipResultState) { bind<UserHistoryMembershipResultState>() } // uji coba ui
    singleOf(::UserMembershipsRepository) { bind<UserMembershipsRepository>() }
    singleOf(::UserMembershipDataSource) { bind<UserMembershipDataSource>() }
    singleOf(::UserMembershipApiService) { bind<UserMembershipApiService>() }
}

val launchAppModule = module {
    /// Launch App
    singleOf(::LaunchAppResultState) { bind<LaunchAppResultState>() }
    singleOf(::LaunchAppVM) { bind<LaunchAppVM>() }
    singleOf(::LaunchAppUseCase) { bind<LaunchAppUseCase>() }

    singleOf(::UpdateOSUseCase) { bind<UpdateOSUseCase>() }
}

val updateContentModule = module {
    /// General Content
    singleOf(::GeneralContentUseCase) { bind<IGeneralContentUseCase>() }

    singleOf(::GeneralContentRepository) { bind<IGeneralContentRepository>() }
    singleOf(::GeneralContentApiService) { bind<IGeneralContentApiService>() }
}

val accountModule = module {
    /// Account Use Case
    singleOf(::AboutAppResultState) { bind<AboutAppResultState>() }
    singleOf(::AccountVM) { bind<AccountVM>() }
    singleOf(::AccountUseCase) { bind<AccountUseCase>() }
}

val myRubriksModule = module {
    /// My Rubrik Use Case
    singleOf(::MyRubriksState) { bind<MyRubriksState>() }
    singleOf(::MyRubriksApiService) { bind<MyRubriksApiService>() }
    singleOf(::MyRubriksRepository) { bind<MyRubriksRepository>() }
    singleOf(::MyRubriksUseCase) { bind<MyRubriksUseCase>() }
}

val updateTokenModule = module {
    singleOf(::TokenInterceptor) { bind<TokenInterceptor>() }
    singleOf(::TokenUseCase) { bind<TokenUseCase>() }
}

val helperModule = module {
    singleOf(::SettingsHelper) { bind<SettingsHelper>() }
    singleOf(::UserDataHelper) { bind<UserDataHelper>() }
    singleOf(::DecodeJWT) { bind<DecodeJWT>() }

//    EXMAPLE
    singleOf(::ExampleNoArgModuleSettingsHelper) { bind<ExampleNoArgModuleSettingsHelper>() }
    singleOf(::ListenerNoArgModuleSettingsHelper) { bind<ListenerNoArgModuleSettingsHelper>() }
    singleOf(::SerializationNoArgModuleSettingsHelper) { bind<SerializationNoArgModuleSettingsHelper>() }
    singleOf(::CoroutineNoArgModuleSettingsHelper) { bind<CoroutineNoArgModuleSettingsHelper>() }
}

val netDataModule = module {
    singleOf(::NetworkApiService) { bind<INetworkApiService>() }
    singleOf(::NetworkVM) { bind<INetworkVM>() }

}

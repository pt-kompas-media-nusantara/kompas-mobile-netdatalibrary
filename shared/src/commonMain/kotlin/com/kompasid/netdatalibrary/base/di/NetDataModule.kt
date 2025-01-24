package com.kompasid.netdatalibrary.base.di

import com.kompasid.netdatalibrary.core.data.userDetail.model.local.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.NetworkApiService.INetworkApiService
import com.kompasid.netdatalibrary.base.network.NetworkApiService.NetworkApiService
import com.kompasid.netdatalibrary.base.network.NetworkVM.INetworkVM
import com.kompasid.netdatalibrary.base.network.NetworkVM.NetworkVM
import com.kompasid.netdatalibrary.core.data.generalContent.network.GeneralContentApiServiceImpl
import com.kompasid.netdatalibrary.core.data.generalContent.repository.GeneralContentRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.network.LoginEmailApiService
import com.kompasid.netdatalibrary.core.data.loginEmail.models.local.LoginEmailDataSource
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginEmailRepository
import com.kompasid.netdatalibrary.core.data.loginGuest.network.LoginGuestApiService
import com.kompasid.netdatalibrary.core.data.loginGuest.model.local.LoginGuestDataSource
import com.kompasid.netdatalibrary.core.data.loginGuest.repository.LoginGuestRepository
import com.kompasid.netdatalibrary.core.data.logout.network.LogoutApiService
import com.kompasid.netdatalibrary.core.data.logout.model.local.LogoutDataSource
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.data.refreshToken.model.local.RefreshTokenDataSource
import com.kompasid.netdatalibrary.core.data.refreshToken.network.RefreshTokenApiService
import com.kompasid.netdatalibrary.core.data.refreshToken.repository.RefreshTokenRepository
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userMembershipHistory.network.UserMembershipHistoryApiService
import com.kompasid.netdatalibrary.core.data.userMembershipHistory.model.local.UserMembershipHistoryDataSource
import com.kompasid.netdatalibrary.core.data.generalContent.network.GeneralContentApiService
import com.kompasid.netdatalibrary.core.data.generalContent.usecase.GeneralContentUseCaseImpl
import com.kompasid.netdatalibrary.core.data.userMembershipHistory.repository.UserMembershipHistoryRepository
import com.kompasid.netdatalibrary.core.domain.generalContent.repository.IGeneralContentRepository
import com.kompasid.netdatalibrary.core.domain.myAccount.usecase.MyAccountUseCase
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.GeneralContentUseCase
import com.kompasid.netdatalibrary.core.domain.personalInfo.usecase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.presentation.general_content_presentation.GeneralContentVM
import com.kompasid.netdatalibrary.core.presentation.authPresentation.AuthVM
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::LoginGuestApiService) { bind<LoginGuestApiService>() }
}



val netDataModule = module {

    singleOf(::NetworkApiService) { bind<INetworkApiService>() }
    singleOf(::NetworkVM) { bind<INetworkVM>() }

    // Auth
    /**
    Login Email
    Login by Guest
    Refresh Token
    Logout
     */
    singleOf(::AuthVM) { bind<AuthVM>() }

    singleOf(::AuthUseCase) { bind<AuthUseCase>() }

    singleOf(::LoginGuestRepository) { bind<LoginGuestRepository>() }
    singleOf(::LoginGuestDataSource) { bind<LoginGuestDataSource>() }
    singleOf(::LoginGuestApiService) { bind<LoginGuestApiService>() }

    singleOf(::RefreshTokenRepository) { bind<RefreshTokenRepository>() }
    singleOf(::RefreshTokenDataSource) { bind<RefreshTokenDataSource>() }
    singleOf(::RefreshTokenApiService) { bind<RefreshTokenApiService>() }

    singleOf(::LoginEmailRepository) { bind<LoginEmailRepository>() }
    singleOf(::LoginEmailDataSource) { bind<LoginEmailDataSource>() }
    singleOf(::LoginEmailApiService) { bind<LoginEmailApiService>() }

    singleOf(::LogoutRepository) { bind<LogoutRepository>() }
    singleOf(::LogoutDataSource) { bind<LogoutDataSource>() }
    singleOf(::LogoutApiService) { bind<LogoutApiService>() }

    // Personal Info
    /**
    User Detail
    User Membership History
     */
    singleOf(::PersonalInfoUseCase) { bind<PersonalInfoUseCase>() }

    singleOf(::UserDetailRepository) { bind<UserDetailRepository>() }
    singleOf(::UserDetailDataSource) { bind<UserDetailDataSource>() }
    singleOf(::UserDetailApiService) { bind<UserDetailApiService>() }

    singleOf(::UserMembershipHistoryRepository) { bind<UserMembershipHistoryRepository>() }
    singleOf(::UserMembershipHistoryDataSource) { bind<UserMembershipHistoryDataSource>() }
    singleOf(::UserMembershipHistoryApiService) { bind<UserMembershipHistoryApiService>() }

    /**
    General Content
     */
    singleOf(::GeneralContentVM) { bind<GeneralContentVM>() }

    singleOf(::GeneralContentUseCaseImpl) { bind<GeneralContentUseCase>() }

    singleOf(::GeneralContentRepository) { bind<IGeneralContentRepository>() }
    singleOf(::GeneralContentApiServiceImpl) { bind<GeneralContentApiService>() }

    singleOf(::MyAccountUseCase) { bind<MyAccountUseCase>() }
    singleOf(::SettingsUseCase) { bind<SettingsUseCase>() }

}

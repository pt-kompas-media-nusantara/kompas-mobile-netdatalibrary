package com.kompasid.netdatalibrary.base.di

import com.kompasid.app.netdatamodule.Example.Data.UserDetailData.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.NetworkApiService.INetworkApiService
import com.kompasid.netdatalibrary.base.network.NetworkApiService.NetworkApiService
import com.kompasid.netdatalibrary.base.network.NetworkVM.INetworkVM
import com.kompasid.netdatalibrary.base.network.NetworkVM.NetworkVM
import com.kompasid.netdatalibrary.netData.data.appIconData.AppIconApiService
import com.kompasid.netdatalibrary.netData.data.appIconData.AppIconRepository
import com.kompasid.netdatalibrary.netData.data.loginEmailData.LoginEmailApiService
import com.kompasid.netdatalibrary.netData.data.loginEmailData.LoginEmailDataSource
import com.kompasid.netdatalibrary.netData.data.loginEmailData.LoginEmailRepository
import com.kompasid.netdatalibrary.netData.data.loginGuestData.LoginGuestApiService
import com.kompasid.netdatalibrary.netData.data.loginGuestData.LoginGuestDataSource
import com.kompasid.netdatalibrary.netData.data.loginGuestData.LoginGuestRepository
import com.kompasid.netdatalibrary.netData.data.logoutData.LogoutApiService
import com.kompasid.netdatalibrary.netData.data.logoutData.LogoutDatasource
import com.kompasid.netdatalibrary.netData.data.logoutData.LogoutRepository
import com.kompasid.netdatalibrary.netData.data.refreshTokenData.RefreshTokenDataSource
import com.kompasid.netdatalibrary.netData.data.refreshTokenData.RefreshTokenApiService
import com.kompasid.netdatalibrary.netData.data.refreshTokenData.RefreshTokenRepository
import com.kompasid.netdatalibrary.netData.data.userDetailData.UserDetailApiService
import com.kompasid.netdatalibrary.netData.data.userDetailData.UserDetailRepository
import com.kompasid.netdatalibrary.netData.data.userMembershipHistoryData.UserMembershipHistoryApiService
import com.kompasid.netdatalibrary.netData.data.userMembershipHistoryData.UserMembershipHistoryDataSource
import com.kompasid.netdatalibrary.netData.data.userMembershipHistoryData.UserMembershipHistoryRepository
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.MyAccountUseCase
import com.kompasid.netdatalibrary.netData.domain.SettingsDomain.SettingsUseCase
import com.kompasid.netdatalibrary.netData.domain.appIconDomain.AppIconUseCase
import com.kompasid.netdatalibrary.netData.domain.authDomain.AuthUseCase
import com.kompasid.netdatalibrary.netData.domain.personalInfoDomain.PersonalInfoUseCase
import com.kompasid.netdatalibrary.netData.presentation.appIconPresentation.AppIconVM
import com.kompasid.netdatalibrary.netData.presentation.authPresentation.AuthVM
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerUseCase
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
    singleOf(::LogoutDatasource) { bind<LogoutDatasource>() }
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
    App Icon
     */
    singleOf(::AppIconVM) { bind<AppIconVM>() }

    singleOf(::AppIconUseCase) { bind<AppIconUseCase>() }

    singleOf(::AppIconRepository) { bind<AppIconRepository>() }
    singleOf(::AppIconApiService) { bind<AppIconApiService>() }


    singleOf(::MyAccountUseCase) { bind<MyAccountUseCase>() }
    singleOf(::SettingsUseCase) { bind<SettingsUseCase>() }
    single { TrackerUseCase }
//    singleOf(::TrackerManager) { bind<TrackerManager>() }
//    singleOf(::TrackerDelegate) { bind<TrackerManager>() }
//    singleOf(::TrackerManager) { bind<ITrackerDelegate>() }
//    single { TrackerManager.apply { delegate = get() } }

}

//val trackerModule = module {
//    // Registrasi ITrackerDelegate sebagai implementasi yang konkret
//    single<ITrackerDelegate> { ITrackerDelegate() }
//
//    // Registrasi TrackerManager dan mengatur dependency-nya
//    single {
//        TrackerManager().apply {
//            delegate = get<ITrackerDelegate>() // Inject ITrackerDelegate ke TrackerManager
//        }
//    }
//}

//singleOf(::GeneralContentUseCaseImpl) { bind<GeneralContentUseCase>() }
//singleOf(::GeneralContentRepository) { bind<IGeneralContentRepository>() }
//singleOf(::GeneralContentApiServiceImpl) { bind<GeneralContentApiService>() }
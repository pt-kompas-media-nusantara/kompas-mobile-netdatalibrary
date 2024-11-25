package com.kompasid.netdatalibrary.base.di

import com.kompasid.app.netdatamodule.Example.Data.UserDetailData.UserDetailDataSource
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
import com.kompasid.netdatalibrary.netData.domain.authDomain.AuthUseCase
import com.kompasid.netdatalibrary.netData.domain.personalInfoDomain.PersonalInfoUseCase
import org.koin.dsl.module

class NetDataModule {}

val netDataModule = module {
    // Auth
    /**
    Login Email
    Login by Guest
    Refresh Token
    Logout
     */
    single<AuthUseCase> { AuthUseCase(get(), get(), get(), get()) }

    single<LoginGuestRepository> { LoginGuestRepository(get(), get()) }
    single<LoginGuestDataSource> { LoginGuestDataSource(get()) }
    single<LoginGuestApiService> { LoginGuestApiService(get()) }

    single<RefreshTokenRepository> { RefreshTokenRepository(get(), get()) }
    single<RefreshTokenDataSource> { RefreshTokenDataSource(get()) }
    single<RefreshTokenApiService> { RefreshTokenApiService(get(), get()) }

    single<LoginEmailRepository> { LoginEmailRepository(get(), get()) }
    single<LoginEmailDataSource> { LoginEmailDataSource(get()) }
    single<LoginEmailApiService> { LoginEmailApiService(get()) }

    single<LogoutRepository> { LogoutRepository(get(), get(), get()) }
    single<LogoutDatasource> { LogoutDatasource(get()) }
    single<LogoutApiService> { LogoutApiService(get()) }

    // Personal Info
    /**
    User Detail
    User Membership History
     */
    single<PersonalInfoUseCase> { PersonalInfoUseCase(get(), get()) }

    single<UserDetailRepository> { UserDetailRepository(get(), get()) }
    single<UserDetailDataSource> { UserDetailDataSource(get()) }
    single<UserDetailApiService> { UserDetailApiService(get(), get()) }

    single<UserMembershipHistoryRepository> { UserMembershipHistoryRepository(get(), get()) }
    single<UserMembershipHistoryDataSource> { UserMembershipHistoryDataSource(get()) }
    single<UserMembershipHistoryApiService> { UserMembershipHistoryApiService(get(), get()) }
}


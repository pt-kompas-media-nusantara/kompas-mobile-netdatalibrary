package com.kompasid.netdatalibrary.core.data.loginEmail.repository

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.models.local.LoginEmailDataSource
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.network.LoginEmailApiService
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class LoginEmailRepository(
    private val loginEmailApiService: LoginEmailApiService,
    private val loginEmailDataSource: LoginEmailDataSource,
) : ILoginEmailRepository {

    override suspend fun postLoginEmail(request: LoginEmailRequest): Results<Unit, NetworkError> {
        return when (val result = loginEmailApiService.postLoginEmail(request)) {
            is ApiResults.Success -> {
                result.data.data?.run {
                    loginEmailDataSource.save(
                        accessToken ?: "",
                        refreshToken ?: "",
                        isVerified ?: false,
                        deviceKeyId ?: "",
                        isSocial ?: false
                    )
                }
                Results.Success(Unit)
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}

data class LoginInterceptor(
    var accessToken: String = "",
    var refreshToken: String = "",
)

class LoginEmailResultState(
    private val settingsHelper: SettingsHelper,
) : BaseVM() {
    val loginInterceptor: StateFlow<LoginInterceptor> =
        combine(
            settingsHelper.getStringFlow(KeySettingsType.ACCESS_TOKEN).map { it ?: "" },
            settingsHelper.getStringFlow(KeySettingsType.REFRESH_TOKEN).map { it ?: "" },
        ) { accessToken, refreshToken ->
            LoginInterceptor(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                LoginInterceptor()
            )
}

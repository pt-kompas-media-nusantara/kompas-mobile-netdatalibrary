package com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dataSource

import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.interceptor.PurchaseTokenCheckResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PurchaseTokenCheckDataSource(
    private val settingsHelper: SettingsHelper
) {

    suspend fun save(resultInterceptor: PurchaseTokenCheckResInterceptor) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.EMAIL_BY_PURCHASE_TOKEN, resultInterceptor.email),
                settingsHelper.saveAsync(this, KeySettingsType.REGISTER_BY_PURCHASE_TOKEN, resultInterceptor.registerBy),
                settingsHelper.saveAsync(this, KeySettingsType.IS_AUTO_LOGIN, resultInterceptor.email.isNotEmpty())
            ).awaitAll()
        }
    }

}
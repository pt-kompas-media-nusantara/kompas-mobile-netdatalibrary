package com.kompasid.netdatalibrary.core.domain.purchaseToken.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.enums.ForceUpdateType
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.interceptor.PurchaseTokenCheckResInterceptor
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.repository.PurchaseTokenCheckRepository
import com.kompasid.netdatalibrary.core.domain.forceUpdate.model.ForceUpdateInterceptor
import com.kompasid.netdatalibrary.core.domain.forceUpdate.model.MinMaxVersionAppInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.helpers.logged

class PurchaseTokenUseCase(
    private val purchaseTokenCheckRepository: PurchaseTokenCheckRepository
) {

    suspend fun purchaseTokenCheck(datetime: String, signature: String): Results<PurchaseTokenCheckResInterceptor, NetworkError> {
        return try {
            when (val result = purchaseTokenCheckRepository.purchaseTokenCheck(datetime, signature).logged(prefix = "UseCase: purchaseTokenCheck")) {
                is Results.Error -> Results.Error(result.error)

                is Results.Success -> {

                    return Results.Success(
                        PurchaseTokenCheckResInterceptor(
                            email = result.data.email,
                            registerBy = result.data.registerBy,
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}
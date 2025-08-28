package com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dataSource.PurchaseTokenCheckDataSource
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.interceptor.PurchaseTokenCheckResInterceptor
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.network.PurchaseTokenCheckApiService

class PurchaseTokenCheckRepository(
    private val purchaseTokenCheckApiService: PurchaseTokenCheckApiService,
    private val purchaseTokenCheckDataSource: PurchaseTokenCheckDataSource
) : IPurchaseTokenCheckRepository {

    suspend fun purchaseTokenCheck(datetime: String, signature: String): Results<PurchaseTokenCheckResInterceptor, NetworkError> {
        return try {
            when (val result = purchaseTokenCheckApiService.purchaseTokenCheck(datetime, signature)) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()
                    purchaseTokenCheckDataSource.save(resultInterceptor)

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

}
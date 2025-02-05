package com.kompasid.netdatalibrary.core.data.generalContent.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.generalContent.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.generalContent.network.GeneralContentApiService
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.generalContent.repository.IGeneralContentRepository

// nurirppan__
// ini nggak pakai interface untuk di unit test?
// btw gue setuju yu data transform di taruh di repository. namun untuk usecase sendiri kita pakai ini ya
//✅ Menggabungkan beberapa repository
//✅ Menjalankan operasi paralel, serial, atau concurrency jika diperlukan

// TODO: Wahyu - Ini interface semua mas, buat yang use case iya nanti bisa di define ulang,
//  karena general content ini sifatnya standalone gaada bergantung yang lain jadi usecasenya cuma jadi jembatan
class GeneralContentRepository(
    private val generalContentServices: GeneralContentApiService
) : IGeneralContentRepository {

    override suspend fun getGeneralData(): Results<GeneralContentInterceptor, NetworkError> {
        return when (val result = generalContentServices.getGeneralContent()) {
            is ApiResults.Success -> {
                Results.Success(result.data.toInterceptor())
            }

            is ApiResults.Error -> {
                Results.Error(result.error)
            }
        }
    }
}
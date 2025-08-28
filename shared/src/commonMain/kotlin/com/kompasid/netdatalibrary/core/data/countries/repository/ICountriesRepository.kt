package com.kompasid.netdatalibrary.core.data.countries.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.countries.dto.interceptor.CountriesResInterceptor

interface ICountriesRepository {
    suspend fun countries(): Results<List<CountriesResInterceptor>, NetworkError>
}
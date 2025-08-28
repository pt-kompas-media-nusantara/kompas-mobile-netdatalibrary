package com.kompasid.netdatalibrary.core.data.countries.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.countries.dto.interceptor.CountriesResInterceptor
import com.kompasid.netdatalibrary.core.data.countries.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.countries.network.CountriesApiService
import com.kompasid.netdatalibrary.core.data.countries.resultState.CountriesResultState

class CountriesRepository(
    private val countriesApiService: CountriesApiService,
    private val countriesResultState: CountriesResultState
) : ICountriesRepository {
    override suspend fun countries(): Results<List<CountriesResInterceptor>, NetworkError> {
        return when (val result = countriesApiService.countries()) {
            is ApiResults.Success -> {
                result.data.toInterceptor().also { resultInterceptor ->

                    countriesResultState.apply {
                        if (countries.value != resultInterceptor) update(
                            resultInterceptor
                        )
                    }
                }.let { Results.Success(it) }
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}
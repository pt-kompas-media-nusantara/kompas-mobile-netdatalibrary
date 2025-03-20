package com.kompasid.netdatalibrary.core.data.countries.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.countries.dto.response.CountriesResponse

interface ICountriesApiService {
    suspend fun countries(): ApiResults<CountriesResponse, NetworkError>
}
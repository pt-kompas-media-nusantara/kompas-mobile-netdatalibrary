package com.kompasid.netdatalibrary.core.data.countries.mappers

import com.kompasid.netdatalibrary.core.data.countries.dto.interceptor.CountriesResInterceptor
import com.kompasid.netdatalibrary.core.data.countries.dto.response.CountriesResponse

fun CountriesResponse.toInterceptor(): List<CountriesResInterceptor> {
    return data?.map {
        CountriesResInterceptor(
            it.id ?: "",
            it.name ?: "",
        )
    } ?: emptyList()

}
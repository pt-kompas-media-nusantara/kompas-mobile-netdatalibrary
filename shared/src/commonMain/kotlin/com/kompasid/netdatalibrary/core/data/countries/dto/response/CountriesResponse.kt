package com.kompasid.netdatalibrary.core.data.countries.dto.response

data class CountriesResponse(
    val code: Int? = null,
    val data: List<DataCountries>? = null,
    val message: String? = null,
)

data class DataCountries(
    val id: String? = null,
    val name: String? = null,
)

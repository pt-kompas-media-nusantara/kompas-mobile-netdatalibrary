package com.kompasid.netdatalibrary.core.data.countries.resultState

import com.kompasid.netdatalibrary.core.data.countries.dto.interceptor.CountriesResInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CountriesResultState {
    private val _countries = MutableStateFlow<List<CountriesResInterceptor>>(emptyList())
    var countries: StateFlow<List<CountriesResInterceptor>> = _countries.asStateFlow()

    fun update(data: List<CountriesResInterceptor>) {
        _countries.value = data
    }
}
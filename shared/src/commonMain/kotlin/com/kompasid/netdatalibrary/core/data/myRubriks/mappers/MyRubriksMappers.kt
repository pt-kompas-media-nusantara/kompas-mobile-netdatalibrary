package com.kompasid.netdatalibrary.core.data.myRubriks.mappers

import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.OldMyRubriksResponse
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor

fun OldMyRubriksResponse.toInterceptor(): List<MyRubriksResInterceptor> {
    return data?.map {
        MyRubriksResInterceptor(
            it.banner ?: "",
            it.desc ?: "",
            it.isChoosen ?: false,
            it.text ?: "",
            it.value ?: "",
        )
    } ?: emptyList()

}
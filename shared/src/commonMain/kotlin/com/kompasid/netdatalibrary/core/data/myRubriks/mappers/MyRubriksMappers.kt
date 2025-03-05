package com.kompasid.netdatalibrary.core.data.myRubriks.mappers

import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.OldMyRubriksResponse
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor

fun OldMyRubriksResponse.toInterceptor(): List<MyRubriksResInterceptor> {
    return data.orEmpty().mapNotNull { item ->
        item?.let {
            MyRubriksResInterceptor(
                banner = it.banner ?: "",
                desc = it.desc ?: "",
                isChoosen = it.isChoosen ?: false,
                text = it.text ?: "",
                value = it.value ?: ""
            )
        }
    }
}

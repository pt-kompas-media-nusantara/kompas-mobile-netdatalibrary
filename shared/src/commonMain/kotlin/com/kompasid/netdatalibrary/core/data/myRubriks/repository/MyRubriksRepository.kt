package com.kompasid.netdatalibrary.core.data.myRubriks.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.dataSource.MyRubriksDataSource
import com.kompasid.netdatalibrary.core.data.myRubriks.network.MyRubriksApiService
import com.kompasid.netdatalibrary.core.data.myRubriks.resultState.MyRubriksResultState
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor


class MyRubriksRepository(
    private val myRubriksApiService: MyRubriksApiService,
    private val myRubriksDataSource: MyRubriksDataSource,
    private val myRubriksResultState: MyRubriksResultState
) : IMyRubriksRepository {

    override suspend fun getMyRubriks(): Results<List<MyRubriksResInterceptor>, NetworkError> {
        return when (val result = myRubriksApiService.getMyRubriks()) {
            is ApiResults.Success -> {
                result.data.toInterceptor().also { resultInterceptor ->
                    myRubriksDataSource.save(resultInterceptor)

                    myRubriksResultState.apply {
                        if (allRubriks.value != resultInterceptor) updateAllRubriks(
                            resultInterceptor
                        )
                        if (myRubriks.value != resultInterceptor) {
                            val resultMyRubriks = resultInterceptor.filter { it.isChoosen }
                            updateMyRubriks(resultMyRubriks)
                        }
                    }
                }.let { Results.Success(it) }
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}

//Perbaikan yang Dilakukan:
//Menggunakan apply untuk mengurangi kode berulang
//
//Mengurangi redundant checks dan update menggunakan apply { } untuk myRubriksResultState.
//Memanfaatkan let untuk menangani resultInterceptor dengan lebih bersih
//
//Menghindari deklarasi variabel yang tidak perlu.
//Menggunakan return when
//
//Menghindari return dalam setiap kondisi, sehingga lebih idiomatis.
//Menggunakan also untuk menyimpan data lebih rapi
//
//resultInterceptor sekarang disimpan ke data source tanpa mengganggu flow utama.

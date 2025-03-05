package com.kompasid.netdatalibrary.core.data.myRubriks.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.network.MyRubriksApiService
import com.kompasid.netdatalibrary.core.data.myRubriks.resultState.MyRubriksState
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MyRubriksRepository(
    private val myRubriksApiService: MyRubriksApiService,
    private val myRubriksState: MyRubriksState
) : IMyRubriksRepository {

    override suspend fun getMyRubriks(): Results<List<MyRubriksResInterceptor>, NetworkError> =
        runCatching {
            myRubriksApiService.getRubrikList()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is ApiResults.Success -> {
                        val resultInterceptor = result.data.toInterceptor()

                        coroutineScope {
                            launch {
                                myRubriksState.updateAllRubriks(resultInterceptor)
                            }
                            launch {
                                val resultMyRubriks = resultInterceptor.filter { it.isChoosen }
                                myRubriksState.updateMyRubriks(resultMyRubriks)
                            }
                        }

                        Results.Success(resultInterceptor)
                    }

                    is ApiResults.Error -> Results.Error(result.error)
                }
            },
            onFailure = { exception ->
                Results.Error(NetworkError.Error(exception))
            }
        )


    override suspend fun saveMyRubriks(request: SaveMyRubrikRequest): Results<Unit, NetworkError> =
        coroutineScope {
            val saveResult = myRubriksApiService.saveMyRubriks(request)

            when (saveResult) {
                is ApiResults.Success -> {
                    val refreshDeferred = async { getMyRubriks() }
                    when (val refreshResult = refreshDeferred.await()) {
                        is Results.Success -> Results.Success(Unit) // Jika keduanya sukses
                        is Results.Error -> Results.Error(refreshResult.error) // Jika refresh gagal, return error dari getMyRubriks()
                    }
                }

                is ApiResults.Error -> Results.Error(saveResult.error) // Jika penyimpanan gagal, langsung return error
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

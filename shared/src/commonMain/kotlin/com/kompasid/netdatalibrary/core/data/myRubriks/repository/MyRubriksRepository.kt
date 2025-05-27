package com.kompasid.netdatalibrary.core.data.myRubriks.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.network.MyRubriksApiService
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.RubricSelectionListResInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MyRubriksRepository(
    private val myRubriksApiService: MyRubriksApiService,
    private val settingsHelper: SettingsHelper// nurirppan__:  harus pakai datasource
) : IMyRubriksRepository {

    suspend fun getMyRubriks(): Results<Pair<List<RubricSelectionListResInterceptor>, List<MyRubriksResInterceptor>>, NetworkError> =
        try {
            val result = myRubriksApiService.getRubrikList()

            when (result) {
                is ApiResults.Success -> {
                    coroutineScope {
                        val resultRubricSelectionListDeferred = async { result.data.toInterceptor() }
                        val resultRubricSelectionList = resultRubricSelectionListDeferred.await()

                        val resultMyRubriksDeferred = async {
                            resultRubricSelectionList
                                .filter { it.isChoosen }
                                .map { MyRubriksResInterceptor(it.banner, it.desc, it.isChoosen, it.text, it.value) }
                        }
                        val resultMyRubriks = resultMyRubriksDeferred.await()

                        // nurirppan__: ini harus di save dalam bentuk json
                        settingsHelper.save(KeySettingsType.RUBRIK_PILIHANKU, resultMyRubriks)

                        Results.Success(Pair(resultRubricSelectionList, resultMyRubriks))
                    }
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }


    suspend fun saveMyRubriks(request: SaveMyRubrikRequest): Results<Unit, NetworkError> =
        try {
            val saveResult = myRubriksApiService.saveMyRubriks(request)

            when (saveResult) {
                is ApiResults.Success -> Results.Success(Unit)
                // nurirppan__: ini juga blm validasi selanjutnya
                is ApiResults.Error -> Results.Error(saveResult.error)
            }
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
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

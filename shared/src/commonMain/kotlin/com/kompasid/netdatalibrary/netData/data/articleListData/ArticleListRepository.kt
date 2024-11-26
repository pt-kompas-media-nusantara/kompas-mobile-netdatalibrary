package com.kompasid.netdatalibrary.netData.data.articleListData

import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results
import com.kompasid.netdatalibrary.netData.data.articleListData.dto.ArticleListResponse

class ArticleListRepository(
    private val articleListApiService: ArticleListApiService
) {
    suspend fun getArticleList(type: ArticleListType): Results<List<ArticleListResponse>, NetworkError> {
        when (val result = articleListApiService.getArticleList(type)) {
            is ApiResults.Success -> {

                val response = result.data

                return Results.Success(response)
            }
            // Jika terjadi error
            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}
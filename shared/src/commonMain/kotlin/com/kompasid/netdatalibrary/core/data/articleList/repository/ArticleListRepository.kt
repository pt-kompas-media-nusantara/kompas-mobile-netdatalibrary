package com.kompasid.netdatalibrary.core.data.articleList.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.articleList.network.ArticleListApiService
import com.kompasid.netdatalibrary.core.data.articleList.utils.ArticleListType
import com.kompasid.netdatalibrary.core.data.articleList.model.dto.ArticleListResponse

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
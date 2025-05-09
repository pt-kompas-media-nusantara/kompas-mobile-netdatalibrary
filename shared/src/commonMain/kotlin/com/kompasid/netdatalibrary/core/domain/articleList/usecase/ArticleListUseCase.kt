package com.kompasid.netdatalibrary.core.domain.articleList.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.articleList.repository.ArticleListRepository
import com.kompasid.netdatalibrary.core.data.articleList.utils.ArticleListType
import com.kompasid.netdatalibrary.core.domain.articleList.interceptor.ArticleListResInterceptor
import com.kompasid.netdatalibrary.core.domain.articleList.mapper.ArticleListMapper

class ArticleListUseCase(
    private val articleListRepository: ArticleListRepository
) {

    suspend fun articleList(type: ArticleListType): Results<Pair<ArticleListType, List<ArticleListResInterceptor>>, NetworkError> {
        when (val response = articleListRepository.getArticleList(type)) {
            is Results.Success -> {
                val mappedArticles = ArticleListMapper().mapArticleList(response.data)


                return Results.Success(Pair(type, mappedArticles))
            }

            is Results.Error -> {
                return Results.Error(response.error)
            }
        }
    }

//    suspend fun articleListTagar(type: ArticleListType): Result<Pair<ArticleListType, List<ArticleListResInterceptor>>, NetworkError> {
//        when (val response = articleListRepository.getArticleList(type)) {
//            is Result.Success -> {
//                val mappedArticles = ArticleListMapper().mapArticleListTagar(response.data)
//
//
//                return Result.Success(Pair(type, mappedArticles))
//            }
//
//            is Result.Error -> {
//                return Result.Error(response.error)
//            }
//        }
//    }

}


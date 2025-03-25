package com.kompasid.netdatalibrary.core.domain.articleList.mapper

import com.kompasid.netdatalibrary.helpers.RelativeTimeFormatter
import com.kompasid.netdatalibrary.core.data.belum.articleList.model.dto.ArticleListResponse
import com.kompasid.netdatalibrary.core.data.belum.articleList.model.dto.Category
import com.kompasid.netdatalibrary.core.data.belum.articleList.model.dto.Thumbnails
import com.kompasid.netdatalibrary.core.domain.articleList.interceptor.ArticleListResInterceptor
import com.kompasid.netdatalibrary.core.domain.articleList.interceptor.ArticleType

class ArticleListMapper {

    suspend fun mapArticleList(articleRaw: List<ArticleListResponse>): List<ArticleListResInterceptor> =
        articleRaw.map { raw ->
            ArticleListResInterceptor(
                raw.title ?: "", // DONE | judul
                raw.name
                    ?: "", // DONE | id judul : bank-telepon-demokrat-vs-america-pac-ujung-tombak-kampanye-harris-vs-trump
                raw.excerpt ?: "", // DONE |
                setImgUrl(raw.thumbnails), // DONE |
                setNameOfSlug(raw.category), // DONE |
                setSlugId(raw.category), // DONE |
                raw.imageCounter ?: 0, // DONE | ini khusus untuk foto
                setMultimediaType(raw.category), // DONE | ini untuk menentukan jenis chartumb pada list artikel
                "nurirppan",
                setPublishDate(raw.publishedDate ?: ""), // DONE |
                raw.audio ?: "",
            )
        }

//    fun mapArticleListTagar(articleRaw: List<ArticleListResponse>): List<ArticleListTagarResInterceptor> =
//        articleRaw.map { raw ->
//            ArticleListResInterceptor(
//                raw.title ?: "", // DONE | judul
//                raw.name
//                    ?: "", // DONE | id judul : bank-telepon-demokrat-vs-america-pac-ujung-tombak-kampanye-harris-vs-trump
//                raw.excerpt ?: "", // DONE |
//                setImgUrl(raw.thumbnails), // DONE |
//                setNameOfSlug(raw.category), // DONE |
//                setSlugId(raw.category), // DONE |
//                raw.imageCounter ?: 0, // DONE | ini khusus untuk foto
//                setMultimediaType(raw.category), // DONE | ini untuk menentukan jenis chartumb pada list artikel
//                "nurirppan",
//                setPublishDate(raw.publishedDate ?: ""), // DONE |
//                raw.audio ?: "",
//            )
//        }

    suspend fun setPublishDate(publishedDate: String): String {
        return RelativeTimeFormatter().getRelativeTime(publishedDate)
    }

    suspend fun setMultimediaType(category: List<Category?>?): ArticleType {
        return when {
            category?.any { it?.slug == "foto-cerita" || it?.slug == "galeri-foto" || it?.slug == "klinik-foto-kompas" || it?.slug == "foto" } == true -> ArticleType.FOTO
            category?.any { it?.slug == "video" || it?.slug == "berita-video" || it?.slug == "feature" || it?.slug == "dokumenter" || it?.slug == "videografik" } == true -> ArticleType.VIDEO
            category?.any { it?.slug == "taja" } == true -> ArticleType.TAJA
            else -> ArticleType.STANDARD
        }
    }

    suspend fun setNameOfSlug(slug: List<Category?>?): String {
        return slug?.firstOrNull()?.name ?: "nurirppan setNameOfSlug" // Handle null or empty slug
    }

    suspend fun setSlugId(slug: List<Category?>?): String {
        return slug?.firstOrNull()?.slug ?: "nurirppan setSlugId" // Handle null or empty slug
    }

    suspend fun setImgUrl(thumbnails: Thumbnails?): String {
        return when {
            thumbnails?.sizes?.medium != null -> thumbnails.sizes.medium.permalink ?: ""
            thumbnails?.sizes?.large != null -> thumbnails.sizes.large.permalink ?: ""
            thumbnails?.sizes?.mediumLarge != null -> thumbnails.sizes.mediumLarge.permalink ?: ""
            thumbnails?.sizes?.postThumbnail != null -> thumbnails.sizes.postThumbnail.permalink
                ?: ""

            else -> ""
        }
    }
}

package com.kompasid.netdatalibrary.core.data.articleList.model.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ArticleListResponse(
    @SerialName("articleByLine")
    val articleByLine: String? = null,
    @SerialName("audio")
    val audio: String? = null,
    @SerialName("bookmark")
    val bookmark: Boolean? = null,
    @SerialName("category")
    val category: List<Category?>? = null,
    @SerialName("createdDate")
    val createdDate: String? = null,
    @SerialName("edition")
    val edition: String? = null,
    @SerialName("editors")
    val editors: List<Editor?>? = null,
    @SerialName("excerpt")
    val excerpt: String? = null,
    @SerialName("external")
    val `external`: Boolean? = null,
    @SerialName("headline")
    val headline: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("imageCounter")
    val imageCounter: Int? = null,
    @SerialName("isFreemium")
    val isFreemium: Boolean? = null,
    @SerialName("keyword")
    val keyword: String? = null,
    @SerialName("labels")
    val labels: List<String?>? = null,
    @SerialName("meta")
    val meta: MetaX? = null,
    @SerialName("modifiedDate")
    val modifiedDate: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("postTag")
    val postTag: List<PostTag?>? = null,
    @SerialName("publishedDate")
    val publishedDate: String? = null,
    @SerialName("publishedDateGmt")
    val publishedDateGmt: String? = null,
    @SerialName("site")
    val site: String? = null,
    @SerialName("source")
    val source: String? = null,
//    @SerialName("sponsors")
//    val sponsors: List<Any??>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("thumbnails")
    val thumbnails: Thumbnails? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("type")
    val type: String? = null
)

@Serializable
data class Category(
    @SerialName("name")
    val name: String? = null,
    @SerialName("slug")
    val slug: String? = null
)

@Serializable
data class Editor(
    @SerialName("name")
    val name: String? = null
)

@Serializable
data class MetaX(
    @SerialName("cache")
    val cache: Boolean? = null
)

@Serializable
data class PostTag(
    @SerialName("name")
    val name: String? = null,
    @SerialName("slug")
    val slug: String? = null
)

@Serializable
data class Thumbnails(
    @SerialName("meta")
    val meta: MetaXX? = null,
    @SerialName("sizes")
    val sizes: Sizes? = null
)

@Serializable
data class MetaXX(
    @SerialName("photographerName")
    val photographerName: String? = null
)

@Serializable
data class Sizes(
    @SerialName("large")
    val large: Large? = null,
    @SerialName("medium")
    val medium: Medium? = null,
    @SerialName("mediumLarge")
    val mediumLarge: MediumLarge? = null,
//    @SerialName("original")
//    val original: Any?,
    @SerialName("postThumbnail")
    val postThumbnail: PostThumbnail? = null,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerialName("thumbnailMedium")
    val thumbnailMedium: ThumbnailMedium? = null,
    @SerialName("thumbnailSquareMedium")
    val thumbnailSquareMedium: ThumbnailSquareMedium? = null
)

@Serializable
data class Large(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("width")
    val width: Int? = null
)

@Serializable
data class Medium(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("width")
    val width: Int? = null
)

@Serializable
data class MediumLarge(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("width")
    val width: Int? = null
)

@Serializable
data class PostThumbnail(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("width")
    val width: Int? = null
)

@Serializable
data class Thumbnail(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("width")
    val width: Int? = null
)

@Serializable
data class ThumbnailMedium(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("width")
    val width: Int? = null
)

@Serializable
data class ThumbnailSquareMedium(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("width")
    val width: Int? = null
)




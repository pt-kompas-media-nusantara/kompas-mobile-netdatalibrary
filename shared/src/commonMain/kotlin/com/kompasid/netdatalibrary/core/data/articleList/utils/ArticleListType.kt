package com.kompasid.netdatalibrary.core.data.articleList.utils


sealed class ArticleListType(
    val endpoint: String, val version: String, val path: String
) {

    class LandingPageTagar(
        val cursor: String = "",
        val name: String = "",
    ) : ArticleListType(
        endpoint = "https://cds.kompas.id", version = "/api/v2", path = "article/list/tag/$name"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    class Pilihanku(
        val cursor: String = ""
    ) : ArticleListType(
        endpoint = "https://cds.kompas.id", version = "/api/v2", path = "article/pilihanku"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    class FavoritPembaca(
        val cursor: String = ""
    ) : ArticleListType(
        endpoint = "https://cds.kompas.id", version = "/api/v2", path = "article/konten-promo"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    class Bookmarks(
        val cursor: String = ""
    ) : ArticleListType(
        endpoint = "https://cds.kompas.id", version = "/api/v2", path = "article/bookmark"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    class BeritaUtama(
        val cursor: String = ""
    ) : ArticleListType(
        endpoint = "https://cds.kompas.id", version = "/api/v2", path = "article/list/tag/utama"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    class Terpopuler(
        val cursor: String = ""
    ) : ArticleListType(
        endpoint = "https://cds.kompas.id", version = "/api/v2", path = "article/popular-article"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    class Terbaru(
        val cursor: String = ""
    ) : ArticleListType(
        endpoint = "https://cds.kompas.id", version = "/api/v2", path = "article/list"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    class BacaLainnya(
        val pageUrl: String,
        val guid: String,
        val slugs: String,
        val cursor: String = ""
    ) : ArticleListType(
        endpoint = "https://ai.kompas.id", version = "/api/v1", path = "recommendation"
    ) {
        override fun fullUrl(): String {
            val queryParams = mapOf(
                "page_url" to pageUrl,
                "page_type" to "read",
                "item_type" to "articles",
                "guid" to guid,
                "slugs" to slugs,
                "cursor" to cursor,
            )

            val baseUrl = "$endpoint$version/$path"
            val queryString = queryParams.entries.joinToString("&") { (key, value) ->
                "${key}=${value}"
            }
            return "$baseUrl?$queryString"
        }
    }

    open fun fullUrl(): String {
        return "$endpoint$version/$path"
    }
}


/*
fun contoh(type: ArticleListType) {
    println(type.endpoint)
    println(type.version)
    println(type.url)
}

// Contoh penggunaan
fun main() {
    contoh(ArticleListType.BeritaUtama)
    // Output:
    // https://cds.kompas.id
    // /v2
    // /article/list/tag/utama
}

val queryParams = mapOf(
    "page_url" to "https://www.kompas.id/baca/investigasi/2024/10/21/mencari-jawaban-dari-kapan-nikah",
    "page_type" to "read",
    "item_type" to "articles",
    "guid" to "947baf96-67cd-45a7-ac30-e900651a96c0",
    "slugs" to "investigasi,desk-investigasi-dan-jurnalisme-data"
)

val url = ArticleListType.Bookmarks.fullUrl(queryParams)
println(url)
// Output: https://cds.kompas.id/api/v2/article/bookmark?page_url=https%3A%2F%2Fwww%2Ekompas%2Eid%2Fbaca%2Finvestigasi%2F2024%2F10%2F21%2Fmencari%2Djawaban%2Ddari%2Dkapan%2Dnikah&page_type=read&item_type=articles&guid=947baf96-67cd-45a7-ac30-e900651a96c0&slugs=investigasi%2Cdesk-investigasi-dan-jurnalisme-data

val bacaLainnya = ArticleListType.BacaLainnya(
    pageUrl = "https://www.kompas.id/baca/investigasi/2024/10/21/mencari-jawaban-dari-kapan-nikah",
    pageType = "read",
    itemType = "articles",
    guid = "947baf96-67cd-45a7-ac30-e900651a96c0"
)
*/
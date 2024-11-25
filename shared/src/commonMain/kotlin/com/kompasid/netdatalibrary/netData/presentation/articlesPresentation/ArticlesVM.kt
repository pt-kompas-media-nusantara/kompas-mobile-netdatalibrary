package com.kompasid.netdatalibrary.netData.presentation.articlesPresentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.netData.domain.authDomain.AuthUseCase

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.random.Random
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long


class ArticlesVM(
    private val settings: SettingsDataSource,
    private val authUseCase: AuthUseCase, // cek lagi
//    private val articleListUseCase: ArticleListUseCase, // home (V), baca lainnya(), baca nanti(), landing page pencarian(), landing page tagar()
//    private val articleDetailUseCase: ArticleDetailUseCase, // belum : interceptor & mapping. article detail, baca lainnya, chipsbar
//    private val bookmarkUseCase: BookmarkUseCase, // belum : database local
//    private val rubrikPilihankuUseCase: RubrikPilihankuUseCase, // belum : database local dan data local
//    private val appIconUseCase: AppIconUseCase, // belum : database local
//    private val questionAnswerUseCase: QuestionAnswerUseCase, // done
//    private val widgetIframeUseCase: WidgetIframeUseCase, // done
//    private val subscriptionUseCase: SubscriptionUseCase, // done
//    private val repolaUseCase: RepolaUseCase, // done
//    private val deviceManagerUseCase: DeviceManagerUseCase, // done
) : BaseVM() {
//    private val _articleState: MutableStateFlow<ArticlesState> =
//        MutableStateFlow(ArticlesState(loading = true))
//    val articlesState: StateFlow<ArticlesState> get() = _articleState

//    private val _articleListState: MutableStateFlow<ArticleListState> =
//        MutableStateFlow(ArticleListState())
//    val articleListState: StateFlow<ArticleListState> get() = _articleListState

    val accessToken: StateFlow<String?> = settings.getStringFlow(KeySettingsType.ACCESS_TOKEN)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val refreshToken: StateFlow<String?> = settings.getStringFlow(KeySettingsType.REFRESH_TOKEN)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val isVerified: StateFlow<Boolean?> = settings.getBooleanFlow(KeySettingsType.IS_VERIFIED)
        .map { it ?: false }
        .stateIn(scope, SharingStarted.WhileSubscribed(), false)

    val deviceKeyId: StateFlow<String?> = settings.getStringFlow(KeySettingsType.DEVICE_KEY_ID)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val isSocial: StateFlow<Boolean?> = settings.getBooleanFlow(KeySettingsType.IS_SOCIAL)
        .map { it ?: false }
        .stateIn(scope, SharingStarted.WhileSubscribed(), false)

    val userId: StateFlow<String?> = settings.getStringFlow(KeySettingsType.USER_ID)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val firstName: StateFlow<String?> = settings.getStringFlow(KeySettingsType.FIRST_NAME)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val lastName: StateFlow<String?> = settings.getStringFlow(KeySettingsType.LAST_NAME)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val email: StateFlow<String?> = settings.getStringFlow(KeySettingsType.EMAIL)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val userGuid: StateFlow<String?> = settings.getStringFlow(KeySettingsType.USER_GUID)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val phoneNumber: StateFlow<String?> = settings.getStringFlow(KeySettingsType.PHONE_NUMBER)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val countryCode: StateFlow<String?> = settings.getStringFlow(KeySettingsType.COUNTRY_CODE)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val country: StateFlow<String?> = settings.getStringFlow(KeySettingsType.COUNTRY)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val province: StateFlow<String?> = settings.getStringFlow(KeySettingsType.PROVINCE)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val city: StateFlow<String?> = settings.getStringFlow(KeySettingsType.CITY)
        .map { it ?: "" }
        .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val membershipExpired: StateFlow<String?> =
        settings.getStringFlow(KeySettingsType.MEMBERSHIP_EXPIRED)
            .map { it ?: "" }
            .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val membershipActive: StateFlow<String?> =
        settings.getStringFlow(KeySettingsType.MEMBERSHIP_ACTIVE)
            .map { it ?: "" }
            .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val membershipStartDate: StateFlow<String?> =
        settings.getStringFlow(KeySettingsType.MEMBERSHIP_START_DATE)
            .map { it ?: "" }
            .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val membershipEndDate: StateFlow<String?> =
        settings.getStringFlow(KeySettingsType.MEMBERSHIP_END_DATE)
            .map { it ?: "" }
            .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    val membershipTotalGracePeriod: StateFlow<Int?> =
        settings.getIntFlow(KeySettingsType.MEMBERSHIP_TOTAL_GRACE_PERIOD)
            .map { it ?: 0 }
            .stateIn(scope, SharingStarted.WhileSubscribed(), 0)

    val membershipGracePeriode: StateFlow<Boolean?> =
        settings.getBooleanFlow(KeySettingsType.MEMBERSHIP_GRACE_PERIOD)
            .map { it ?: false }
            .stateIn(scope, SharingStarted.WhileSubscribed(), false)

    fun postLoginGuest() {
        scope.launch {
            authUseCase.loginAnon()
        }
    }

    fun postLogout() {
        scope.launch {
            authUseCase.logout()
        }
    }


//    fun articleDetail() {
//        scope.launch {
//
//            // article detail - biasa
////            val result = articleDetailUseCase.fetchArticleDetailsWithRelatedData(
////                "urgensi-alokasi-dana-masyarakat-adat-dan-komunitas-lokal",
////                ArticleListType.BacaLainnya(
////                    "urgensi-alokasi-dana-masyarakat-adat-dan-komunitas-lokal",
////                    settings.load(KeySettingsType.USER_GUID, "-"),
////                    "opini,analisis-budaya,desk-budaya",
////                ),
////                "urgensi-alokasi-dana-masyarakat-adat-dan-komunitas-lokal",
////            )
//
//            // article detail - video
////            val result = articleDetailUseCase.fetchArticleDetailsWithRelatedData(
////                "kolaborasi-antarlini-mewujudkan-target-pertumbuhan-ekonomi-8-persen",
////                ArticleListType.BacaLainnya(
////                    "kolaborasi-antarlini-mewujudkan-target-pertumbuhan-ekonomi-8-persen",
////                    settings.load(KeySettingsType.USER_GUID, "-"),
////                    "video,berita-video,desk-multimedia",
////                ),
////                "kolaborasi-antarlini-mewujudkan-target-pertumbuhan-ekonomi-8-persen",
////            )
//
//            // article detail - foto
//            val result = articleDetailUseCase.fetchArticleDetailsWithRelatedData(
//                "melestarikan-arsitektur-tradisional-dan-kearifan-lokal",
//                ArticleListType.BacaLainnya(
//                    "melestarikan-arsitektur-tradisional-dan-kearifan-lokal",
//                    settings.load(KeySettingsType.USER_GUID, "-"),
//                    "foto,galeri-foto,nusantara,desk-foto",
//                ),
//                "melestarikan-arsitektur-tradisional-dan-kearifan-lokal",
//            )
//
//            when (result) {
//                is Results.Error -> {
//                    when (val error = result.error) {
//                        is NetworkError.RequestTimeout -> {
//                            println("Request timeout occurred")
//                        }
//
//                        is NetworkError.Unauthorized -> {
//                            println("Unauthorized access")
//                        }
//
//                        is NetworkError.NoInternet -> {
//                            println("No internet connection")
//                        }
//
//                        is NetworkError.ServerError -> {
//                            println("Server error occurred")
//                        }
//
//                        is NetworkError.NotFound -> {
//                            println("Resource not found")
//                        }
//
//                        is NetworkError.Technical -> {
//                            println("Technical error: Code ${error.code}, Message: ${error.message}")
//                        }
//
//                        is NetworkError.Error -> {
//                            println("Error: ${error.throwable.message}")
//                        }
//
//                        else -> {
//                            println("Unknown error occurred")
//                        }
//                    }
//                }
//
//                is Results.Success -> {
//                    val contentList = result.data.articleDetail.content
//                    contentList.forEach { content ->
//                        when (content) {
//                            is ArticleDetailContent.Title -> {
//                                Logger.debug { "Title: ${content.content}" }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Excerpt -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.ArticleByLine -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.PublishedDate -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Text -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Image -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Blockquote -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Ul -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Url -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Caption -> {
//                                Logger.debug { content.toString() }
//                                // Gunakan di view
//                            }
//
//                            is ArticleDetailContent.Editors -> {
//                                Logger.debug { content.toString() }
//                            }
//
//                            is ArticleDetailContent.KompasVideo -> {
//                                Logger.debug { content.toString() }
//                            }
//                        }
//                    }
//
//                    Logger.debug {
//                        result.data.articleDetail.relatedTopics.toString()
//                    }
//                    Logger.debug {
//                        result.data.relatedArticles.toString()
//                    }
//                    Logger.debug {
//                        result.data.comments.toString()
//                    }
//                    Logger.debug {
//                        result.data.widgetData.toString()
//                    }
//                }
//            }
//        }
//    }
//
//    fun postLoginByEmail() {
//        scope.launch {
//            // harus ada validasi di repositorynya biar tidak perlu validasi ulang di use case
//            val result = authUseCase.loginByEmail(
//                LoginEmailRequest(
//                    "nur.irfan@kompas.com",
//                    "Nurirppankompas@28",
//                    "testKMP",
//                    "testKMP",
//                )
//            )
//
//            Logger.debug {
//                result.toString()
//            }
//
//            when (result) {
//                is Results.Error -> {
//                    when (val error = result.error) {
//                        is NetworkError.RequestTimeout -> {
//                            println("Request timeout occurred")
//                        }
//
//                        is NetworkError.Unauthorized -> {
//                            println("Unauthorized access")
//                        }
//
//                        is NetworkError.NoInternet -> {
//                            println("No internet connection")
//                        }
//
//                        is NetworkError.ServerError -> {
//                            println("Server error occurred")
//                        }
//
//                        is NetworkError.NotFound -> {
//                            println("Resource not found")
//                        }
//
//                        is NetworkError.Technical -> {
//                            println("Technical error: Code ${error.code}, Message: ${error.message}")
//                        }
//
//                        is NetworkError.Error -> {
//                            println("Error: ${error.throwable.message}")
//                        }
//
//                        else -> {
//                            println("Unknown error occurred")
//                        }
//                    }
//                }
//
//                is Results.Success -> {
//                    println("Success")
//                }
//            }
//        }
//    }
//
//    fun getArticle() {
//        scope.launch {
//            _articleState.emit(
//                ArticlesState(
//                    loading = true,
//                    articlesExample = _articleState.value.articlesExample
//                )
//            )
//
//            val fetchArticles = useCase.getArticles()
//
//            // useCase.postLoginGuest()
//            _articleState.emit(ArticlesState(articlesExample = fetchArticles))
//        }
//    }
//
//    fun isTokenExpired(exp: Long): Boolean {
//        // Mendapatkan waktu saat ini dalam detik
//        val currentTime = Clock.System.now().epochSeconds
//
//        // Membandingkan waktu kedaluwarsa token dengan waktu saat ini
//        return currentTime >= exp
//    }
//
//
//    fun getAllArticleList() {
//        scope.launch {
//            val resultBeritaUtama =
//                async { articleListUseCase.articleList(ArticleListType.BeritaUtama()) }
//            val resultTerbaru = async { articleListUseCase.articleList(ArticleListType.Terbaru()) }
//            val resultPilihanku =
//                async { articleListUseCase.articleList(ArticleListType.Pilihanku()) }
//            val resultFavoritPembaca =
//                async { articleListUseCase.articleList(ArticleListType.FavoritPembaca()) }
//            val resultTerpopuler =
//                async { articleListUseCase.articleList(ArticleListType.Terpopuler()) }
//
//            val beritaUtamaData = resultBeritaUtama.await()
//            val terbaruData = resultTerbaru.await()
//            val pilihankuData = resultPilihanku.await()
//            val favoritPembacaData = resultFavoritPembaca.await()
//            val terpopulerData = resultTerpopuler.await()
//
//            Logger.debug { beritaUtamaData.toString() }
//            Logger.debug { terbaruData.toString() }
//            Logger.debug { pilihankuData.toString() }
//            Logger.debug { favoritPembacaData.toString() }
//            Logger.debug { terpopulerData.toString() }
//
////            when (val result = resultBeritaUtama.await()) {
////                is Result.Error -> {
////                    when (val error = result.error) {
////                        is NetworkError.RequestTimeout -> {
////                            println("Request timeout occurred")
////                        }
////
////                        is NetworkError.Unauthorized -> {
////                            println("Unauthorized access")
////                        }
////
////                        is NetworkError.NoInternet -> {
////                            println("No internet connection")
////                        }
////
////                        is NetworkError.ServerError -> {
////                            println("Server error occurred")
////                        }
////
////                        is NetworkError.NotFound -> {
////                            println("Resource not found")
////                        }
////
////                        is NetworkError.Technical -> {
////                            println("Technical error: Code ${error.code}, Message: ${error.message}")
////                        }
////
////                        is NetworkError.Error -> {
////                            println("Error: ${error.throwable.message}")
////                        }
////
////                        else -> {
////                            println("Unknown error occurred")
////                        }
////                    }
////                }
////
////                is Result.Success -> {
////                    println("Success")
////                }
////            }
////
////            when (val result = resultTerbaru.await()) {
////                is Result.Error -> {
////                    when (val error = result.error) {
////                        is NetworkError.RequestTimeout -> {
////                            println("Request timeout occurred")
////                        }
////
////                        is NetworkError.Unauthorized -> {
////                            println("Unauthorized access")
////                        }
////
////                        is NetworkError.NoInternet -> {
////                            println("No internet connection")
////                        }
////
////                        is NetworkError.ServerError -> {
////                            println("Server error occurred")
////                        }
////
////                        is NetworkError.NotFound -> {
////                            println("Resource not found")
////                        }
////
////                        is NetworkError.Technical -> {
////                            println("Technical error: Code ${error.code}, Message: ${error.message}")
////                        }
////
////                        is NetworkError.Error -> {
////                            println("Error: ${error.throwable.message}")
////                        }
////
////                        else -> {
////                            println("Unknown error occurred")
////                        }
////                    }
////                }
////
////                is Result.Success -> {
////                    println("Success")
////                }
////            }
////
////            when (val result = resultPilihanku.await()) {
////                is Result.Error -> {
////                    when (val error = result.error) {
////                        is NetworkError.RequestTimeout -> {
////                            println("Request timeout occurred")
////                        }
////
////                        is NetworkError.Unauthorized -> {
////                            println("Unauthorized access")
////                        }
////
////                        is NetworkError.NoInternet -> {
////                            println("No internet connection")
////                        }
////
////                        is NetworkError.ServerError -> {
////                            println("Server error occurred")
////                        }
////
////                        is NetworkError.NotFound -> {
////                            println("Resource not found")
////                        }
////
////                        is NetworkError.Technical -> {
////                            println("Technical error: Code ${error.code}, Message: ${error.message}")
////                        }
////
////                        is NetworkError.Error -> {
////                            println("Error: ${error.throwable.message}")
////                        }
////
////                        else -> {
////                            println("Unknown error occurred")
////                        }
////                    }
////                }
////
////                is Result.Success -> {
////                    println("Success")
////                }
////            }
////
////            when (val result = resultFavoritPembaca.await()) {
////                is Result.Error -> {
////                    when (val error = result.error) {
////                        is NetworkError.RequestTimeout -> {
////                            println("Request timeout occurred")
////                        }
////
////                        is NetworkError.Unauthorized -> {
////                            println("Unauthorized access")
////                        }
////
////                        is NetworkError.NoInternet -> {
////                            println("No internet connection")
////                        }
////
////                        is NetworkError.ServerError -> {
////                            println("Server error occurred")
////                        }
////
////                        is NetworkError.NotFound -> {
////                            println("Resource not found")
////                        }
////
////                        is NetworkError.Technical -> {
////                            println("Technical error: Code ${error.code}, Message: ${error.message}")
////                        }
////
////                        is NetworkError.Error -> {
////                            println("Error: ${error.throwable.message}")
////                        }
////
////                        else -> {
////                            println("Unknown error occurred")
////                        }
////                    }
////                }
////
////                is Result.Success -> {
////                    println("Success")
////                }
////            }
////
////            when (val result = resultTerpopuler.await()) {
////                is Result.Error -> {
////                    when (val error = result.error) {
////                        is NetworkError.RequestTimeout -> {
////                            println("Request timeout occurred")
////                        }
////
////                        is NetworkError.Unauthorized -> {
////                            println("Unauthorized access")
////                        }
////
////                        is NetworkError.NoInternet -> {
////                            println("No internet connection")
////                        }
////
////                        is NetworkError.ServerError -> {
////                            println("Server error occurred")
////                        }
////
////                        is NetworkError.NotFound -> {
////                            println("Resource not found")
////                        }
////
////                        is NetworkError.Technical -> {
////                            println("Technical error: Code ${error.code}, Message: ${error.message}")
////                        }
////
////                        is NetworkError.Error -> {
////                            println("Error: ${error.throwable.message}")
////                        }
////
////                        else -> {
////                            println("Unknown error occurred")
////                        }
////                    }
////                }
////
////                is Result.Success -> {
////                    println("Success")
////                }
////            }
//
//
//        }
//    }
//
//    fun getArticleBookmark() {
//        scope.launch {
//            val result = articleListUseCase.articleList(ArticleListType.Bookmarks())
//
//            when (result) {
//                is Results.Error -> {
//                    when (val error = result.error) {
//                        is NetworkError.RequestTimeout -> {
//                            println("Request timeout occurred")
//                        }
//
//                        is NetworkError.Unauthorized -> {
//                            println("Unauthorized access")
//                        }
//
//                        is NetworkError.NoInternet -> {
//                            println("No internet connection")
//                        }
//
//                        is NetworkError.ServerError -> {
//                            println("Server error occurred")
//                        }
//
//                        is NetworkError.NotFound -> {
//                            println("Resource not found")
//                        }
//
//                        is NetworkError.Technical -> {
//                            println("Technical error: Code ${error.code}, Message: ${error.message}")
//                        }
//
//                        is NetworkError.Error -> {
//                            println("Error: ${error.throwable.message}")
//                        }
//
//                        else -> {
//                            println("Unknown error occurred")
//                        }
//                    }
//                }
//
//                is Results.Success -> {
//                    println("Success")
//                }
//            }
//        }
//    }
//
//    fun getLoadMoreArticleBookmark() {
//        scope.launch {
//            val result = articleListUseCase.articleList(ArticleListType.Bookmarks("1730914474"))
//
//            when (result) {
//                is Results.Error -> {
//                    when (val error = result.error) {
//                        is NetworkError.RequestTimeout -> {
//                            println("Request timeout occurred")
//                        }
//
//                        is NetworkError.Unauthorized -> {
//                            println("Unauthorized access")
//                        }
//
//                        is NetworkError.NoInternet -> {
//                            println("No internet connection")
//                        }
//
//                        is NetworkError.ServerError -> {
//                            println("Server error occurred")
//                        }
//
//                        is NetworkError.NotFound -> {
//                            println("Resource not found")
//                        }
//
//                        is NetworkError.Technical -> {
//                            println("Technical error: Code ${error.code}, Message: ${error.message}")
//                        }
//
//                        is NetworkError.Error -> {
//                            println("Error: ${error.throwable.message}")
//                        }
//
//                        else -> {
//                            println("Unknown error occurred")
//                        }
//                    }
//                }
//
//                is Results.Success -> {
//                    println("Success")
//                }
//            }
//        }
//    }
//
//    fun saveArticleBookmark() {
//        scope.launch {
//            bookmarkUseCase.saveBookmark("mengapa-orang-amerika-lebih-percaya-pada-trump")
//        }
//    }
//
//    fun removeArticleBookmark(parameter: String) {
//        scope.launch {
//            bookmarkUseCase.removeSpecificBookmark(parameter)
//        }
//    }
//
//    fun removeAllArticleBookmark() {
//        scope.launch {
//            bookmarkUseCase.removeAllBookmark()
//        }
//    }
//
//    fun rubrikPilihankuList() {
//        scope.launch {
//            val result = rubrikPilihankuUseCase.rubrikPilihankuList()
//
//            when (result) {
//                is Results.Error -> {
//                    when (val error = result.error) {
//                        is NetworkError.RequestTimeout -> {
//                            println("Request timeout occurred")
//                        }
//
//                        is NetworkError.Unauthorized -> {
//                            println("Unauthorized access")
//                        }
//
//                        is NetworkError.NoInternet -> {
//                            println("No internet connection")
//                        }
//
//                        is NetworkError.ServerError -> {
//                            println("Server error occurred")
//                        }
//
//                        is NetworkError.NotFound -> {
//                            println("Resource not found")
//                        }
//
//                        is NetworkError.Technical -> {
//                            println("Technical error: Code ${error.code}, Message: ${error.message}")
//                        }
//
//                        is NetworkError.Error -> {
//                            println("Error: ${error.throwable.message}")
//                        }
//
//                        else -> {
//                            println("Unknown error occurred")
//                        }
//                    }
//                }
//
//                is Results.Success -> {
//                    println("Success")
//                }
//            }
//        }
//    }
//
//    fun callSaveRubrikPilihanku() {
//        val ramdoms = arrayOf(
//            "polhuk",
//            "ekonomi",
//            "opini",
//            "humaniora",
//            "riset",
//            "nusantara",
//            "metro",
//            "internasional",
//            "olahraga",
//            "tokoh",
//            "tutur-visual",
//            "gaya-hidup",
//            "infografik",
//            "jelajah",
//            "video",
//            "foto",
//            "hiburan",
//            "sastra",
//            "buku",
//            "english",
//            "perjalanan",
//            "di-balik-berita",
//            "arsip"
//        )
//
//        // Memilih jumlah acak antara 3 hingga 10 item
//        val randomCount = Random.nextInt(3, 11)
//
//        // Memilih item acak dari array ramdoms
//        val selectedValues = ramdoms.toList().shuffled().take(randomCount).toTypedArray()
//
//        saveRubrikPilihanku(selectedValues)
//    }
//
//    fun saveRubrikPilihanku(values: Array<String>) {
//        scope.launch {
//            val userRubriks = values.map { value ->
//                UserRubrikPilihankuSave(isChoosen = true, value = value)
//            }
//
//            rubrikPilihankuUseCase.saveRubrikPilihanku(
//                RubrikPilihankuSaveRequest(
//                    type = "rubrik",
//                    userRubriks = userRubriks
//                )
//            )
//        }
//    }
//
//    fun qna() {
//        scope.launch {
//            val result = questionAnswerUseCase.questionAnswer()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun appIcon() {
//        scope.launch {
//            val result = appIconUseCase.appIcon()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun widgetIframeBeritaUtama() {
//        scope.launch {
//            val result = widgetIframeUseCase.widgetIframeBeritaUtama()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun widgetIframeArticleDetail() {
//        scope.launch {
//            val result = widgetIframeUseCase.widgetIframeArticleDetail()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun subscriptionLandingPage() {
//        scope.launch {
//            val result = subscriptionUseCase.subscriptionLandingPage()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun subscriptionProducts() {
//        scope.launch {
//            val result = subscriptionUseCase.subscriptionProducts()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun repolaIframe() {
//        scope.launch {
//            val result = repolaUseCase.repolaIframe()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun deviceList() {
//        scope.launch {
//            val result = deviceManagerUseCase.deviceList()
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun revokeDeviceAll() {
//        scope.launch {
//            val result = deviceManagerUseCase.revokeDevice(RevokeDeviceType.All)
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun revokeDeviceSpesific() {
//        scope.launch {
//            val result =
//                deviceManagerUseCase.revokeDevice(RevokeDeviceType.Specific("", ""))
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun landingPageTagar() {
//        scope.launch {
//            val result =
//                articleListUseCase.articleList(
//                    ArticleListType.LandingPageTagar(
//                        "",
//                        "stop-kekerasan-seksual"
//                    )
//                )
//            Logger.debug {
//                result.toString()
//            }
//        }
//    }
//
//    fun updateToken() {
//        val token = settings.load(KeySettingsType.ACCESS_TOKEN, "")
//        val parser = JwtParser()
//        val jsonObject = parser.parseToJsonObject(token)
//        // Ambil nilai 'exp' dari token
//        val exp = jsonObject?.get("exp")?.jsonPrimitive?.long ?: 0L
//
//        Logger.debug {
//            jsonObject.toString()
//        }
//
//        // Cek apakah token sudah kedaluwarsa
//        val isExpired = isTokenExpired(exp)
//        Logger.debug {
//            "Token expired: $isExpired"
//        }
//
//        /*
//            {
//                "data": {
//                    "email": "nur.irfan@kompas.com",
//                    "id": "947baf96-67cd-45a7-ac30-e900651a96c0",
//                    "rt": 1763625404,
//                    "scope": [
//                        "read-article",
//                        "read-profile"
//                    ]
//                },
//                "exp": 1732090304,
//                "iat": 1732089404,
//                "iss": "https://www.kompas.id"
//            }
//         */
//    }
//
//    fun interceptorToken() {
//        settings.save(KeySettingsType.ACCESS_TOKEN, Constants.ACCESS_TOKEN)
//        settings.save(KeySettingsType.REFRESH_TOKEN, Constants.REFRESH_TOKEN)
//
//        getArticleBookmark()
//    }


}


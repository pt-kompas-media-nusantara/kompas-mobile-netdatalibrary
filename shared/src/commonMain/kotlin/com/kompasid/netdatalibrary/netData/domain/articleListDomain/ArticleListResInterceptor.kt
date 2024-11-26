package com.kompasid.netdatalibrary.netData.domain.articleListDomain


data class ArticleListResInterceptor(
    val title: String,
    val name: String,
    val excerpt: String,
    val imgUrl: String,
    val nameOfSlug: String,
    val slugId: String,
    val imageCounter: Int,
    val multimediaType: ArticleType,
    val label: String,
    val publishedDate: String,
    val audioUrl: String,
)

data class ArticleListTagarResInterceptor(
    val title: String,
    val name: String,
    val publishedDate: String,
    val chartumb: List<TagarChartumb>,
    val audio: String,
)

data class TagarChartumb(
    val slugId: String,
    val slugName: String,
    val title: String,
)

enum class ArticleType {
    FOTO,
    VIDEO,
    TAJA,
    STANDARD
}

data class ArticleLabelContent(
    val text: String,
    val backgroundColor: String,
    val textColor: String,
)

enum class ArticleLabelType {
    FREE_ACCESS,
    LIVE_REPORT,
    EXCLUSIVE,
    ANALYSIS,
    KOMPAS_BRIEF;

    val content: ArticleLabelContent
        get() = when (this) {
            FREE_ACCESS -> ArticleLabelContent(
                text = "Bebas Akses",
                backgroundColor = "Color.BLUE10_BLUE10",
                textColor = "Color.ROYAL60_BLUE50"
            )

            LIVE_REPORT -> ArticleLabelContent(
                text = "Reportase Langsung",
                backgroundColor = "Color.RED50_RED30",
                textColor = "Color.WHITE_SURFACE04"
            )

            EXCLUSIVE -> ArticleLabelContent(
                text = "Eksklusif",
                backgroundColor = "Color.LIGHT_GREY60_LIGHT_GREY60",
                textColor = "Color.WHITE_WHITE"
            )

            ANALYSIS -> ArticleLabelContent(
                text = "Analisis",
                backgroundColor = "Color.ORANGE10_ORANGE10",
                textColor = "Color.ORANGE50_ORANGE50"
            )

            KOMPAS_BRIEF -> ArticleLabelContent(
                text = "Kompas Brief",
                backgroundColor = "Color.RED10_RED10",
                textColor = "Color.RED60_RED60"
            )
        }
}


/*
val imgUrl: String,

var availableSizes: String? {
    if let cdsSizes = thumbnailsCDS?.sizes {
        if let medium = cdsSizes.medium {
            return medium.permalink
        }

        if let large = cdsSizes.large {
            return large.permalink
        }

        if let mediumLarge = cdsSizes.mediumLargeKey {
            return mediumLarge.permalink
        }

        if let postThumbnail = cdsSizes.postThumbnail {
            return postThumbnail.permalink
        }
    }

    if let kidSizes = thumbnailsKID {
        return kidSizes
    }

    return self.imageURL
}
 */

/*
val slug: String,
var cardthumSlug: String {
    return !self.category.isEmpty ? (self.category[0].slug ?? "") : ""
}
 */

/*
val multimediaType: String,
// MARK: - ArticleListCategory
struct ArticleListCategory: Codable, Hashable {
    let id: Int?
    let name, slug: String?
}
var articleType: BaseArticleType {
    if self.category.contains(where: { $0.slug == "foto-cerita" }) {
        return .foto
    } else if self.category.contains(where: { $0.slug == "galeri-foto" }) {
        return .foto
    } else if self.category.contains(where: { $0.slug == "klinik-foto-kompas" }) {
        return .foto
    } else if self.category.contains(where: { $0.slug == "foto" }) {
        return .foto
    } else if self.category.contains(where: { $0.slug == "video" }) {
        return .video
    } else if self.category.contains(where: { $0.slug == "berita-video" }) {
        return .video
    } else if self.category.contains(where: { $0.slug == "feature" }) {
        return .video
    } else if self.category.contains(where: { $0.slug == "dokumenter" }) {
        return .video
    } else if self.category.contains(where: { $0.slug == "videografik" }) {
        return .video
    } else if self.category.contains(where: { $0.slug == "taja" }) {
        return .taja
    } else {
        return .standard
    }
}

 */


/*
val label: String,
var multimediaType: MultimediaType {
    if self.articleType == .foto {
        return .foto
    } else if self.articleType == .video {
        return .video
    } else {
        return .none
    }
}


var labelType: ArticleLabelType? {
    if self.status == "live" || self.status.lowercased().contains("live") {
        return .liveReport
    } else if self.postTag.contains(where: { $0.slug?.contains("kompas-brief") == true }) {
        return .kompasBrief
    } else if self.postTag.contains(where: { $0.slug?.contains("eksklusif") == true }) {
        return .exclusive
    } else if self.isFreemium { // Sebelum ini tambah if eksklusif, tapi belum tau ngambil dari mana.
        return .freeAccess
    } else if self.postTag.contains(where: { $0.slug?.contains("analisis") == true }) {
        return .analysis
    } else {
        return nil
    }
}
 */

/*
val publishedDate: String,
extension ArticleList {
    var time: String {
        return BaseDate.convertBigCardthumbList(date: self.publishedDate)
    }

    var publishedInterval: String {
        return self.publishedDate.timeDisplay()
    }

    var publishedDateEpoch: Int {
        return BaseDate.convertStringToDateEpoch(date: self.publishedDate)
    }
}

    func dateCategoryView() -> some View {
        Group {
            if self.rubrikType == .reportaseLangsung {
                Text(self.isIndexRepola ? self.articleList.publishedDate.timeDisplay() : self.articleList.time)
                    .foregroundColor(.lightGrey40_text70)
                    .font(.custom(.fontHindRegular, size: 14)) + Text(self.isIndexRepola ? "" : "Reportase Langsung")
                    .foregroundColor(.lightGrey40_text70)
                    .font(.custom(.fontHindRegular, size: 14))
            } else if !self.isRubrikModelEmpty && self.rubrikType != .beranda { // MARK: - RUBRIK
                Text(self.articleList.time)
                    .foregroundColor(.lightGrey40_text70)
                    .font(.custom(.fontHindRegular, size: 14)) +
                    Text(self.articleList.childRubricName)
                    .foregroundColor(.lightGrey40_text70)
                    .font(.custom(.fontHindRegular, size: 14))
            } else { // MARK: - BERANDA
                Text(self.articleList.time)
                    .foregroundColor(.lightGrey40_text70)
                    .font(.custom(.fontHindRegular, size: 14)) +
                    Text(self.request == .favorite ? self.articleList.keyword : self.articleList.rubric)
                    .foregroundColor(self.request == .favorite ? .lightGrey40_text70 : .royal60_blue30)
                    .font(.custom(.fontHindRegular, size: 14))
            }
        }.onTapGesture {
            if (self.articleList.cardthumSlug != "Tutur Visual" && self.request == .favorite) || (!self.isRubrikModelEmpty && self.rubrikType != .beranda) {
                self.dataSource.onCardthumbTapped()
            } else {
                self.onTappedRubrik()
                self.trackRubricClicked(name: self.articleList.rubricType.title)
            }
        }
    }
 */
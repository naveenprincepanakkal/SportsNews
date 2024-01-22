package com.naveenprince.common.model

/**
 * Created by Naveen.
 */
data class SportsNews(
    val sport: Sport,
    var publicationDate: String?,
    val news: String?,
)

enum class Sport(val sports: String) {
    F1("F1"),
    NBA("NBA"),
    TENNIS("Tennis"),
}

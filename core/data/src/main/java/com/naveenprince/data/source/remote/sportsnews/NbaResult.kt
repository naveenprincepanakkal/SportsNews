package com.naveenprince.data.source.remote.sportsnews


import com.squareup.moshi.Json

data class NbaResult(
    @field:Json(name = "gameNumber")
    var gameNumber: Int?,
    @field:Json(name = "looser")
    var looser: String?,
    @field:Json(name = "mvp")
    var mvp: String?,
    @field:Json(name = "publicationDate")
    var publicationDate: String?,
    @field:Json(name = "tournament")
    var tournament: String?,
    @field:Json(name = "winner")
    var winner: String?,
)
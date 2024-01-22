package com.naveenprince.data.source.remote.sportsnews


import com.squareup.moshi.Json

data class F1Result(
    @field:Json(name = "publicationDate")
    var publicationDate: String?,
    @field:Json(name = "seconds")
    var seconds: Double?,
    @field:Json(name = "tournament")
    var tournament: String?,
    @field:Json(name = "winner")
    var winner: String?,
)
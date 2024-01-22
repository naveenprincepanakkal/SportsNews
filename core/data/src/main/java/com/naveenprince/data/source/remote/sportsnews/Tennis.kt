package com.naveenprince.data.source.remote.sportsnews


import com.squareup.moshi.Json

data class Tennis(
    @field:Json(name = "looser")
    var looser: String?,
    @field:Json(name = "numberOfSets")
    var numberOfSets: Int?,
    @field:Json(name = "publicationDate")
    var publicationDate: String?,
    @field:Json(name = "tournament")
    var tournament: String?,
    @field:Json(name = "winner")
    var winner: String?,
)
package com.naveenprince.data.source.remote.sportsnews


import com.squareup.moshi.Json

data class NewsResponse(
    @field:Json(name = "f1Results")
    var f1Results: List<F1Result>,
    @field:Json(name = "nbaResults")
    var nbaResults: List<NbaResult>,
    @field:Json(name = "Tennis")
    var Tennis: List<Tennis>,
)
package com.naveenprince.domain.mapper

import com.naveenprince.common.utils.Utils.Companion.timeFormatConversion
import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.naveenprince.domain.model.Sport
import com.naveenprince.domain.model.SportsNews

/**
 * Class to map [NewsResponse] to list of [SportsNews]
 *
 * Created by Naveen.
 */
fun NewsResponse.toSportsNews(): MutableList<SportsNews> {
    val newsResults = mutableListOf<SportsNews>()
    for (result in this.f1Results) {
        newsResults.add(
            SportsNews(
                Sport.F1,
                result.publicationDate.timeFormatConversion(),
                "${result.winner} wins ${result.tournament} by ${result.seconds} seconds",
            )
        )
    }
    for (result in this.nbaResults) {
        newsResults.add(
            SportsNews(
                Sport.NBA,
                result.publicationDate.timeFormatConversion(),
                "${result.mvp} leads ${result.winner} to game ${result.gameNumber} " +
                        "win in the ${result.tournament}",
            )
        )
    }
    for (result in this.Tennis) {
        newsResults.add(
            SportsNews(
                Sport.TENNIS,
                result.publicationDate.timeFormatConversion(),
                "${result.tournament}: ${result.winner} wins against " +
                        "${result.looser} in ${result.numberOfSets} sets",
            )
        )
    }
    return newsResults
}


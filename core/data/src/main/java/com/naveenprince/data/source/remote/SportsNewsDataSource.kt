package com.naveenprince.data.source.remote

import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.naveenprince.common.utils.Resource

/**
 * Data Source class for sports news
 *
 * Created by Naveen.
 */
interface SportsNewsDataSource {

    /**
     * To get sports news from service
     */
    suspend fun getSportsNews(): Resource<NewsResponse>

}
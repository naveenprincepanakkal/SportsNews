package com.naveenprince.data.source.remote

import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.naveenprince.common.utils.Resource

/**
 * Created by Naveen.
 */
interface SportsNewsDataSource {

    suspend fun getSportsNews(): Resource<NewsResponse>

}
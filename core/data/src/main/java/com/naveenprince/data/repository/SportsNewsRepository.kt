package com.naveenprince.data.repository

import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.naveenprince.common.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository class for sports news
 *
 * Created by Naveen.
 */
interface SportsNewsRepository {

    /**
     * To get sports news
     */
    fun getSportsNews(): Flow<Resource<NewsResponse>>
}

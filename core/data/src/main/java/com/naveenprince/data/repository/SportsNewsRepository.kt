package com.naveenprince.data.repository

import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.naveenprince.common.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Naveen.
 */
interface SportsNewsRepository {
    fun getSportsNews(): Flow<Resource<NewsResponse>>
}

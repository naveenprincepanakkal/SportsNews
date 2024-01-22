package com.naveenprince.data.repository

import com.naveenprince.data.source.remote.SportsNewsDataSource
import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.naveenprince.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Naveen.
 */
class SportsNewsRepositoryImpl @Inject constructor(private val dataSource: SportsNewsDataSource) :
    SportsNewsRepository {

    override fun getSportsNews(): Flow<Resource<NewsResponse>> = flow {
        emit(dataSource.getSportsNews())
    }

}

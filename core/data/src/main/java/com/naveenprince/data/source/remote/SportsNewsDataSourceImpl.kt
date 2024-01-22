package com.naveenprince.data.source.remote

import com.naveenprince.common.utils.Resource
import com.naveenprince.data.api.SportsNewsApi
import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import java.io.IOException
import javax.inject.Inject

/**
 * Data Source implementation class for sports news
 *
 * Created by Naveen.
 */
class SportsNewsDataSourceImpl @Inject constructor(private val api: SportsNewsApi) :
    SportsNewsDataSource {

    override suspend fun getSportsNews(): Resource<NewsResponse> {
        return try {
            Resource.Success(data = api.getNews())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Network Error")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "An unknown error occurred.")
        }
    }
}
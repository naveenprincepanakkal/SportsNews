package com.naveenprince.data.api

import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import retrofit2.http.POST

/**
 * Created by Naveen.
 */
interface SportsNewsApi {

    @POST("results")
    suspend fun getNews(): NewsResponse

    companion object {
        const val BASE_URL = "https://restest.free.beeceptor.com/"
    }

}

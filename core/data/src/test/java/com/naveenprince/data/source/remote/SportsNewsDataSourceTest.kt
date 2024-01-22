package com.naveenprince.data.source.remote

import com.naveenprince.common.utils.Resource
import com.naveenprince.data.api.SportsNewsApi
import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * Created by Naveen.
 */
class SportsNewsDataSourceTest {

    @Mock
    private lateinit var api: SportsNewsApi

    private lateinit var dataSource: SportsNewsDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = SportsNewsDataSourceImpl(api)
    }

    @Test
    fun getSportsNews_success() = runTest {

        val jsonString = javaClass.getResource("/json/results.json")?.readText()
        val newsResponse = jsonString?.let {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(NewsResponse::class.java).fromJson(it)
        }
        `when`(api.getNews()).thenReturn(newsResponse)

        val result = dataSource.getSportsNews()

        assertTrue(result is Resource.Success)
        assertEquals((result as Resource.Success).data, newsResponse)
    }

    @Test
    fun getSportsNews_networkError() = runTest {
        val errorMsg = "Network Error"
        `when`(api.getNews()).thenThrow(RuntimeException(IOException(errorMsg)))

        val result = dataSource.getSportsNews()

        assertTrue(result is Resource.Error)
        assertTrue(((result as Resource.Error).message?.contains(errorMsg) ?: false))
    }

    @Test
    @Throws
    fun getSportsNews_otherError() = runTest {
        val errorMsg = "Other Error"
        `when`(api.getNews()).thenThrow(RuntimeException(Exception(errorMsg)))

        val result = dataSource.getSportsNews()

        assertTrue(result is Resource.Error)
        assertTrue(((result as Resource.Error).message?.contains(errorMsg) ?: false))

    }

}
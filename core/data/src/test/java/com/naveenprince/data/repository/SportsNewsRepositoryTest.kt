package com.naveenprince.data.repository


import com.naveenprince.common.utils.Resource
import com.naveenprince.data.source.remote.SportsNewsDataSource
import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


/**
 * Created by Naveen.
 */
class SportsNewsRepositoryTest {

    @Mock
    private lateinit var dataSource: SportsNewsDataSource

    private lateinit var repository: SportsNewsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = SportsNewsRepositoryImpl(dataSource)
    }

    @Test
    fun getSportsNews_success() = runTest {

        val jsonString = javaClass.getResource("/json/results.json")?.readText()
        val newsResponse = jsonString?.let {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(NewsResponse::class.java).fromJson(it)
        }

        `when`(dataSource.getSportsNews()).thenReturn(Resource.Success(newsResponse))

        val result = repository.getSportsNews().first()

        assertTrue(result is Resource.Success)
        assertEquals((result as Resource.Success).data, newsResponse)
    }

    @Test
    fun getSportsNews_error() = runTest {

        val errorMsg = "Test Error Message"
        `when`(dataSource.getSportsNews()).thenReturn(Resource.Error(message = errorMsg))

        val result = repository.getSportsNews().first()

        assertTrue(result is Resource.Error)
        assertEquals((result as Resource.Error).message, errorMsg)
    }

}
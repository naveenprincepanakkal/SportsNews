package com.naveenprince.domain.usecase


import com.naveenprince.common.utils.Resource
import com.naveenprince.data.repository.SportsNewsRepository
import com.naveenprince.data.source.remote.sportsnews.NewsResponse
import com.naveenprince.domain.model.Sport
import com.naveenprince.domain.model.SportsNews
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.flowOf
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
class SportsResultsUseCaseTest {

    @Mock
    private lateinit var sportsNewsRepository: SportsNewsRepository

    private lateinit var sportsResultsUseCase: SportsResultsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sportsResultsUseCase = SportsResultsUseCase(sportsNewsRepository)
    }

    @Test
    fun invoke_getsSportsNews_successWithSortedAndMappedResults() = runTest {

        val jsonString = javaClass.getResource("/json/results.json")?.readText()
        val newsResponse = jsonString?.let {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(NewsResponse::class.java).fromJson(it)
        }

        val expectedSortedResults = listOf(
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 11:15:15 PM",
                "Roland Garros: Rafael Nadal wins against Schwartzman  in 3 sets"
            ),
            SportsNews(
                Sport.F1,
                "2020-05-09 08:09:03 PM",
                "Lewis Hamilton wins Silverstone Grand Prix by 5.856 seconds"
            ),
            SportsNews(
                Sport.NBA,
                "2020-05-09 09:15:15 AM",
                "Lebron James leads Lakers to game 6 win in the NBA playoffs"
            ),
        )

        `when`(sportsNewsRepository.getSportsNews()).thenReturn(flowOf(Resource.Success(data = newsResponse)))

        sportsResultsUseCase().collect {
            assertTrue(it is Resource.Success)
            assertEquals((it as Resource.Success).data, expectedSortedResults)
        }
    }

    @Test
    fun invoke_getsSportsNews_serviceError() = runTest {
        val errorMessage = "backend error occurred"

        `when`(sportsNewsRepository.getSportsNews()).thenReturn(flowOf(Resource.Error(message = errorMessage)))

        sportsResultsUseCase().collect {
            assertTrue(it is Resource.Error)
            assertEquals((it as Resource.Error).message, errorMessage)
        }
    }

}
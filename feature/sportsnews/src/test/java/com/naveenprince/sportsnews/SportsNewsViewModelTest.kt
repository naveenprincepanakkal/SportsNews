package com.naveenprince.sportsnews

import com.naveenprince.common.model.Sport
import com.naveenprince.common.model.SportsNews
import com.naveenprince.common.utils.Resource
import com.naveenprince.domain.usecase.SportsResultsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by Naveen.
 */

@ExperimentalCoroutinesApi
class SportsNewsViewModelTest {

    @Mock
    private lateinit var sportsResultsUseCase: SportsResultsUseCase

    private lateinit var viewModel: SportsNewsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun sportsNews_successState() = runTest {
        val sportsResults = listOf(
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 11:15:15 PM",
                "Roland Garros: Rafael Nadal wins against Schwartzman  in 3 sets"
            )
        )

        val successState =
            SportsNewsState(sportsResults = sportsResults, isLoading = false, error = null)
        `when`(sportsResultsUseCase()).thenReturn(flowOf(Resource.Success(data = sportsResults)))

        viewModel = SportsNewsViewModel(sportsResultsUseCase)
        assertEquals(viewModel.newsState.value, successState)

    }

    @Test
    fun sportsNews_errorState() = runTest {
        val errMsg = "Error Message"
        val errorState = SportsNewsState(isLoading = false, error = errMsg)
        `when`(sportsResultsUseCase()).thenReturn(flowOf(Resource.Error(message = errMsg)))

        viewModel = SportsNewsViewModel(sportsResultsUseCase)
        assertEquals(viewModel.newsState.value, errorState)

    }


}

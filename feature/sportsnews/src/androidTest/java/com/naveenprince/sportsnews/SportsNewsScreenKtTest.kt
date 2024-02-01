package com.naveenprince.sportsnews

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.naveenprince.common.utils.Utils
import com.naveenprince.common.utils.Utils.Companion.timeFormatConversion
import com.naveenprince.domain.model.Sport
import com.naveenprince.domain.model.SportsNews
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


/**
 * Created by Naveen.
 */

class SportsNewsScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testLoadingState_usingMock() {
        val viewModel: SportsNewsViewModel = mock()
        val newsStateFlow = MutableStateFlow(SportsNewsState(isLoading = true))
        `when`(viewModel.newsState).thenReturn(newsStateFlow)

        composeTestRule.setContent {
            SportsNewsScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("LOADING").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ERROR").assertIsNotDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.no_news_available)
        ).assertDoesNotExist()
    }

    @Test
    fun testLoadingState() {
        val newsState = SportsNewsState(sportsResults = null, isLoading = true, error = null)
        composeTestRule.setContent {
            SportsNewsScreen(newsState)
        }

        composeTestRule.onNodeWithTag("LOADING").assertIsDisplayed()

        composeTestRule.onNodeWithTag("ERROR").assertIsNotDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.no_news_available)
        ).assertIsNotDisplayed()
    }

    @Test
    fun testErrorState() {
        val errorMessage = "Something went wrong"
        val newsState =
            SportsNewsState(sportsResults = null, isLoading = false, error = errorMessage)
        composeTestRule.setContent {
            SportsNewsScreen(newsState)
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()

        composeTestRule.onNodeWithTag("LOADING").assertIsNotDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.no_news_available)
        ).assertIsNotDisplayed()
    }

    @Test
    fun testEmptyDataState() {
        val newsState =
            SportsNewsState(sportsResults = emptyList(), isLoading = false, error = null)
        composeTestRule.setContent {
            SportsNewsScreen(newsState)
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.no_news_available)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag("LOADING").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("ERROR").assertIsNotDisplayed()
    }

    @Test
    fun testDataDisplay() {

        val sportsResults = listOf(
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 11:15:15 PM",
                "Roland Garros: Rafael Nadal wins against Schwartzman  in 3 sets"
            ),
            SportsNews(
                Sport.F1,
                "2020-05-08 08:09:03 PM",
                "Lewis Hamilton wins Silverstone Grand Prix by 5.856 seconds"
            ),
            SportsNews(
                Sport.NBA,
                "2020-04-08 09:15:15 AM",
                "Lebron James leads Lakers to game 6 win in the NBA playoffs"
            ),
        )

        val newsState =
            SportsNewsState(sportsResults = sportsResults, isLoading = false, error = null)
        composeTestRule.setContent {
            SportsNewsScreen(newsState)
        }

        for (sportsResult in sportsResults) {

            val date = sportsResult.publicationDate?.let {
                it.timeFormatConversion(
                    inputFormat = Utils.TIME_FORMAT_2,
                    outputFormat = Utils.TIME_FORMAT_3
                )
            }

            val time = sportsResult.publicationDate?.timeFormatConversion(
                inputFormat = Utils.TIME_FORMAT_2,
                outputFormat = Utils.TIME_FORMAT_4
            ) ?: composeTestRule.activity.resources.getString(R.string.time_unknown)

            composeTestRule.onNodeWithText(date?.let {
                composeTestRule.activity.resources.getString(
                    R.string.result_for, date
                )
            } ?: composeTestRule.activity.resources.getString(R.string.date_unknown))
                .assertIsDisplayed()

            composeTestRule.onNodeWithText(time).assertIsDisplayed()
            composeTestRule.onNodeWithText(
                sportsResult.news
                    ?: composeTestRule.activity.resources.getString(R.string.news_unknown)
            ).assertIsDisplayed()
        }

        composeTestRule.onNodeWithTag("LOADING").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("ERROR").assertIsNotDisplayed()

    }


}
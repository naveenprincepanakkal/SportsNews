package com.naveenprince.domain.utils


import com.naveenprince.common.utils.Utils
import com.naveenprince.common.utils.Utils.Companion.sortByDescendingWithDate
import com.naveenprince.common.utils.Utils.Companion.timeFormatConversion
import com.naveenprince.domain.model.Sport
import com.naveenprince.domain.model.SportsNews
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import java.util.Locale

/**
 * Created by Naveen.
 */
class UtilsTest {

    @Test
    fun testTimeFormatConversion_default() {
        val inputTime = "May 9, 2020 11:15:15 PM"
        val expectedOutput = "2020-05-09 11:15:15 PM"
        val actualOutput = inputTime.timeFormatConversion()
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testTimeFormatConversion_custom() {
        val inputTime = "2020-05-09 11:15:15 PM"
        val expectedOutput = "11:15 PM"
        val actualOutput =
            inputTime.timeFormatConversion(Utils.TIME_FORMAT_2, Utils.TIME_FORMAT_4, Locale.ENGLISH)
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testTimeFormatConversion_error() {
        val inputTime = "2020-05-09 11:15:15 PM"
        try {
            inputTime.timeFormatConversion()
            fail("Throw exception")
        } catch (e: Exception) {
            assertTrue(e.toString().contains("Exception"))
        }
    }

    @Test
    fun testSortByDescendingWithDate_success() {
        val unSortedList = listOf(
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 09:15:15 AM",
                ""
            ),
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 08:09:03 PM",
                ""
            ),
            SportsNews(
                Sport.TENNIS,
                "2020-09-05 10:10:10 PM",
                ""
            ),
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 11:15:15 PM",
                ""
            ),
        )

        val expectedResult = listOf(
            SportsNews(
                Sport.TENNIS,
                "2020-09-05 10:10:10 PM",
                ""
            ),
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 11:15:15 PM",
                ""
            ),
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 08:09:03 PM",
                ""
            ),
            SportsNews(
                Sport.TENNIS,
                "2020-05-09 09:15:15 AM",
                ""
            ),
        )

        val sortedList = unSortedList.sortByDescendingWithDate({ it.publicationDate })
        assertEquals(expectedResult, sortedList)
    }

}
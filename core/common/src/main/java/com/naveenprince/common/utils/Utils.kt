package com.naveenprince.common.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Naveen.
 */
class Utils {
    companion object {
        private const val TIME_FORMAT_1 = "MMM dd, yyyy hh:mm:ss a"
        const val TIME_FORMAT_2 = "yyyy-MM-dd hh:mm:ss a"
        const val TIME_FORMAT_3 = "yyyy-MM-dd"
        const val TIME_FORMAT_4 = "hh:mm:ss a"

        fun String?.timeFormatConversion(
            inputFormat: String = TIME_FORMAT_1,
            outputFormat: String = TIME_FORMAT_2,
            locale: Locale = Locale.ENGLISH
        ): String? {

            val inputFormatter = SimpleDateFormat(inputFormat, locale)
            val outputFormatter = SimpleDateFormat(outputFormat, locale)

            return try {
                val parsedDate = inputFormatter.parse(this!!)
                outputFormatter.format(parsedDate!!)
            } catch (e: Exception) {
                Log.e(Utils::class.java.simpleName, "Error converting time: ${e.printStackTrace()}")
                null
            }
        }

        fun <T> Iterable<T>.sortByDescendingWithDate(
            dateProperty: (T) -> String?,
            dateFormat: String = TIME_FORMAT_2,
            locale: Locale = Locale.ENGLISH
        ): List<T> {
            val sdf = SimpleDateFormat(dateFormat, locale)
            return sortedByDescending {
                dateProperty(it)?.let { dateString -> sdf.parse(dateString) }
            }
        }

    }
}

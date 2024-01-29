package com.naveenprince.common.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Utils Class for common functions which is used across projects
 *
 * Created by Naveen.
 */
class Utils {
    companion object {
        private const val TIME_FORMAT_1 = "MMM dd, yyyy hh:mm:ss a"
        const val TIME_FORMAT_2 = "yyyy-MM-dd hh:mm:ss a"
        const val TIME_FORMAT_3 = "yyyy-MM-dd"
        const val TIME_FORMAT_4 = "hh:mm:ss a"

        /**
         * Covert time given in one format to another.
         * Supports various TIME FORMAT
         */
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

        /**
         * Sorts an iterable collection of items in descending order based on a date property of each item.
         *
         * dateProperty: (T) -> String?: A lambda function that extracts the date property as a string from an item of type T.
         * It can potentially return null if the date property is missing.
         *
         * return sortedByDescending { ... }: Sorts the iterable in descending order using a custom sorting logic defined in the lambda.
         *
         * Custom sorting logic
         * dateProperty(it)?.let { dateString -> sdf.parse(dateString) }:
         *  Calls dateProperty to get the date string for the current item.
         *  Uses let to safely handle potential null values:
         *   If the date string is not null, parses it using sdf.parse and returns the parsed date for comparison.
         *   If it's null, returns null, effectively placing the item at the end of the sorted list.
         *
         */
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

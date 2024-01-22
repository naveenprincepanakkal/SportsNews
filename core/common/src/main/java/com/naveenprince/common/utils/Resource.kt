package com.naveenprince.common.utils

/**
 * Class used to handle api service response
 *
 * Created by Naveen.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)
}

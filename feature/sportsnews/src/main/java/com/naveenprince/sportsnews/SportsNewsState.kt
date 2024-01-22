package com.naveenprince.sportsnews

import com.naveenprince.common.model.SportsNews


/**
 * Created by Naveen.
 */
data class SportsNewsState(
    val sportsResults: List<SportsNews>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
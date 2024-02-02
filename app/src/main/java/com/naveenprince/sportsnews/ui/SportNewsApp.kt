package com.naveenprince.sportsnews.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.naveenprince.sportsnews.navigation.SportsNewsNavHost

/**
 * Created by Naveen.
 */
@Composable
fun SportsNewsApp() {
    val navController = rememberNavController()
    SportsNewsNavHost(navController = navController)
}

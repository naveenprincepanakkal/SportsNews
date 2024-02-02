package com.naveenprince.sportsnews.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.naveenprince.sportsnews.SportsNewsScreen
import com.naveenprince.ui.home.HomeScreen

/**
 * Created by Naveen.
 */

@Composable
fun SportsNewsNavHost(
    navController: NavHostController,
    startDestination: String = NavigationRoute.HOME.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationRoute.HOME.route) {
            HomeScreen(onNewsClick = { navController.navigate(NavigationRoute.NEWS.route) })
        }
        composable(NavigationRoute.NEWS.route) {
            SportsNewsScreen()
        }
    }
}


enum class NavigationRoute(
    val route: String,
) {
    HOME("home"),
    NEWS("news"),
}

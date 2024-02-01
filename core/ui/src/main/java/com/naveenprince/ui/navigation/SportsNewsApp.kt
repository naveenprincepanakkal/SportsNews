package com.naveenprince.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naveenprince.ui.home.HomeScreen
import com.naveenprince.sportsnews.SportsNewsScreen

/**
 * Created by Naveen.
 */
@Composable
fun SportsNewsApp() {
    val navController = rememberNavController()
    SportsNewsNavHost(navController = navController)
}

@Composable
fun SportsNewsNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavigationRoute.HOME.route) {
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
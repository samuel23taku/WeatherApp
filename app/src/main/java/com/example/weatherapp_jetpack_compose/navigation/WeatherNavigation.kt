package com.example.weatherapp_jetpack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp_jetpack_compose.screens.main.WeatherMainScreen
import com.example.weatherapp_jetpack_compose.screens.WeatherMainViewModel
import com.example.weatherapp_jetpack_compose.screens.search.WeatherSearchScreen
import com.example.weatherapp_jetpack_compose.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}", arguments = listOf(
            navArgument(name = "city"){
                type = NavType.StringType
            }
        )){
            val mainViewModel = hiltViewModel<WeatherMainViewModel>()
            WeatherMainScreen(navController = navController,viewModel=mainViewModel)
        }
        composable(WeatherScreens.SearchScreen.name){
            WeatherSearchScreen(navController = navController)
        }
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }
    }
}
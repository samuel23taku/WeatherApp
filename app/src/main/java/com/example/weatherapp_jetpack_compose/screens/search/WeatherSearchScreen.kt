package com.example.weatherapp_jetpack_compose.screens.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.weatherapp_jetpack_compose.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSearchScreen(navController: NavController){
    Scaffold(
        topBar = {
            WeatherAppBar(
                title ="Search",
                navController = navController, icon = Icons.Default.ArrowBack, isOnMainScreen = false)
        }

    ){requiredPadding->
        Surface(modifier= Modifier.padding(requiredPadding)) {

        }

    }
}
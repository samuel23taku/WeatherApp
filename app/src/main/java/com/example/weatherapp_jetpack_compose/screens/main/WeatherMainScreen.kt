package com.example.weatherapp_jetpack_compose.screens.main

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.weatherapp_jetpack_compose.data.DataOrException
import com.example.weatherapp_jetpack_compose.model.Weather
import com.example.weatherapp_jetpack_compose.screens.WeatherMainViewModel
import com.example.weatherapp_jetpack_compose.widgets.WeatherAppBar

@Composable
fun WeatherMainScreen(navController: NavController, viewModel: WeatherMainViewModel) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(
            loading = true,
        )
    ) {
        value = viewModel.getWeather(city = "lisbon")
    }.value


    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
//        Text("Main Screen ${weatherData.data?.city}")
        MainScaffold(weather = weatherData.data!!,navController=navController)
    } else {
        Text("Testing ${weatherData.loading}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(title = weather.city.name + ",${weather.city.country}",navController= navController,icon = Icons.Default.ArrowBack){
            Log.d("Button CLick","Button clicked")
        }
    }) {
        scaffoldPadding->
        Surface(modifier = Modifier.padding(scaffoldPadding)) {
           MainContent(data = weather)
        }
    }
}

@Composable
fun MainContent(data: Weather) {
    Text("Main Content working : city : ${data.city.name}")
}

package com.example.weatherapp_jetpack_compose.screens.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp_jetpack_compose.data.DataOrException
import com.example.weatherapp_jetpack_compose.model.Weather
import com.example.weatherapp_jetpack_compose.model.WeatherItem
import com.example.weatherapp_jetpack_compose.navigation.WeatherScreens
import com.example.weatherapp_jetpack_compose.screens.viewmodels.WeatherMainViewModel
import com.example.weatherapp_jetpack_compose.utils.formatDate
import com.example.weatherapp_jetpack_compose.utils.formatDecimals
import com.example.weatherapp_jetpack_compose.widgets.*

@Composable
fun WeatherMainScreen(navController: NavController, viewModel: WeatherMainViewModel, city: String?) {
    Log.d("City","MainScreen $city")
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(
            loading = true,
        )
    ) {
        value = viewModel.getWeather(city = city!!)
    }.value


    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
//        Text("Main Screen ${weatherData.data?.city}")
        MainScaffold(weather = weatherData.data!!, navController = navController)
    } else {
        Text("Testing ${weatherData.loading}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + ",${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            }
        ) {
            Log.d("Button CLick", "Button clicked")
        }
    }) { scaffoldPadding ->
        Surface(modifier = Modifier.padding(scaffoldPadding)) {
            MainContent(data = weather)
        }
    }
}

@Composable
fun MainContent(data: Weather) {
    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    val todaysDate = formatDate(data.list[0].dt)
    val todaysTemp = formatDecimals(data.list[0].temp.day)
    val todayWeatherStatus = data.list[0].weather[0].main //snow,rainy,sunny,etc

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todaysDate, style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp), shape = CircleShape, color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = "$todaysTemp°",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(todayWeatherStatus, fontStyle = FontStyle.Italic)

            }

        }
        HumidityWindPressureRow(weather = data.list[0])
        Divider()
        SunsetSunriseRow(weather = data.list[0])
        Text("This Week", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)) {
                this.items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weatherItem = item)
                }
            }
        }
    }
}

package com.example.weatherapp_jetpack_compose.screens.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.weatherapp_jetpack_compose.data.DataOrException
import com.example.weatherapp_jetpack_compose.model.Weather
import com.example.weatherapp_jetpack_compose.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherMainViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    val data: MutableState<DataOrException<Weather, Boolean, Exception>> = mutableStateOf(
        DataOrException(
            data = null, loading = true, e = Exception("")
        )
    )

    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }

}
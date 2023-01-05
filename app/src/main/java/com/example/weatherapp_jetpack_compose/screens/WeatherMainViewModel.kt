package com.example.weatherapp_jetpack_compose.screens

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
//        viewModelScope.launch {
//            if (city.isEmpty()) return@launch
//
//            var response = repository.getWeather(cityQuery = city)
//            data.value.loading = true
//            Log.e("dataVar", "${data.value}")
//            data.value = response
//            Log.e("data", "${data.value.data}")
//            if (data.value.data.toString().isNotEmpty()) {
//                data.value.loading = false
//                Log.e("dataUpdate", "${data.value.loading}")
//            }
//            Log.e("Get","${data}")
//        }
    }

}
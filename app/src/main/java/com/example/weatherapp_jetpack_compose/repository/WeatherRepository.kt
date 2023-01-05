package com.example.weatherapp_jetpack_compose.repository

import android.util.Log
import com.example.weatherapp_jetpack_compose.data.DataOrException
import com.example.weatherapp_jetpack_compose.model.Weather
import com.example.weatherapp_jetpack_compose.network.WeatherApi
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery:String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        }catch (e:Exception){
            Log.e("Exception Data","${e}")
            return DataOrException(e=e)
        }
        Log.e("Data response","$response")
        return DataOrException(data = response, loading = false)
    }
}
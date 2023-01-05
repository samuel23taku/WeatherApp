package com.example.weatherapp_jetpack_compose.model

data class WeatherObject(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
package com.example.weatherapp_jetpack_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp_jetpack_compose.model.Favorite


@Database(entities = [
    Favorite::class
], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao():WeatherDAO
}
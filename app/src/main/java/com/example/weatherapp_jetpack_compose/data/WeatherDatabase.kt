package com.example.weatherapp_jetpack_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp_jetpack_compose.model.Favorite
import com.example.weatherapp_jetpack_compose.model.Units


@Database(
    entities = [
        Favorite::class, Units::class
    ], version = 2, exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}
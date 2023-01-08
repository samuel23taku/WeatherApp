package com.example.weatherapp_jetpack_compose.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp_jetpack_compose.data.WeatherDAO
import com.example.weatherapp_jetpack_compose.data.WeatherDatabase
import com.example.weatherapp_jetpack_compose.network.WeatherApi
import com.example.weatherapp_jetpack_compose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDAO =
        weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context, WeatherDatabase::class.java, "weather_database"
        ).fallbackToDestructiveMigration().build()

}
//Todo (Fix magic Strings)
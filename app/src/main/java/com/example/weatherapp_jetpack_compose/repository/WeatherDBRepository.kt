package com.example.weatherapp_jetpack_compose.repository

import com.example.weatherapp_jetpack_compose.data.WeatherDAO
import com.example.weatherapp_jetpack_compose.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDBRepository @Inject constructor(private val weatherDAO: WeatherDAO) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDAO.getFavorites()

    suspend fun getFavById(city: String): Favorite = weatherDAO.getFavById(city)

    suspend fun insertFavorite(favorite: Favorite) = weatherDAO.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDAO.updateFavorite(favorite)

    suspend fun deleteAllFavorites() = weatherDAO.deleteAllFavorites()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDAO.deleteFavorite(favorite)
}
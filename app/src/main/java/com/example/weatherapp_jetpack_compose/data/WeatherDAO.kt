package com.example.weatherapp_jetpack_compose.data

import androidx.room.*
import com.example.weatherapp_jetpack_compose.model.Favorite
import com.example.weatherapp_jetpack_compose.model.Units
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDAO {
    @Query("SELECT * FROM favorite_table")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_table WHERE city = :city")
    suspend fun getFavById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM settings_table")
    fun getUnits(): Flow<List<Units>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(unit: Units)

    @Query("DELETE FROM settings_table")
    suspend fun deleteUnits()

}
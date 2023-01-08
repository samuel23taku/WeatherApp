package com.example.weatherapp_jetpack_compose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class Units(
    @PrimaryKey
    @ColumnInfo(name = "units")
    val unit: String
)
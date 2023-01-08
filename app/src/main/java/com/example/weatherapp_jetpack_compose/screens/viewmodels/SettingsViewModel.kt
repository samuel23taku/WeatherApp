package com.example.weatherapp_jetpack_compose.screens.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_jetpack_compose.model.Units
import com.example.weatherapp_jetpack_compose.repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherDBRepository
) : ViewModel() {
    private val _unitList = MutableStateFlow<List<Units>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUnits().distinctUntilChanged().collect { listOfUnits ->
                if (listOfUnits.isEmpty()) {
                    Log.d("TAG", "Nothing to show")
                } else {
                    _unitList.value = listOfUnits
                }
            }
        }
    }

    private fun getUnits(): Flow<List<Units>> = repository.getUnits()

    fun insertUnits(unit: Units) = viewModelScope.launch {
        repository.insertUnits(unit)
    }

    fun deleteUnits() = viewModelScope.launch {
        repository.deleteUnits()
    }
}
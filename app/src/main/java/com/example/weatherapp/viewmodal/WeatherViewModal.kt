package com.example.weatherapp.viewmodal

import NetworkChangesReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.modal.api.WeatherApi
import com.example.weatherapp.modal.data.WeatherResponce
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModal @Inject constructor(
    private val api: WeatherApi,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _weather: MutableStateFlow<WeatherResponce?> = MutableStateFlow(null)
    val weather: StateFlow<WeatherResponce?> = _weather.asStateFlow()

    private val networkChangesReceiver = NetworkChangesReceiver{
        loadWeather(lastCity.value)
    }

    var lastCity: MutableStateFlow<String> = MutableStateFlow("")

    init {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkChangesReceiver, filter)
    }

    fun loadWeather(city: String){
        viewModelScope.launch {
           try {
               val responce = api.getCurrentWeather(
                   apiKey = "6918abc7c93f4d8fb85224018250105",
                   city = city
               )
               _weather.value = responce
           }
           catch (e: Exception){
               e.printStackTrace()
           }

        }
    }
}
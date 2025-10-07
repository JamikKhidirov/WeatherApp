package com.example.weatherapp.modal.data

import android.location.Location

data class WeatherResponce(
    val location: LocationResp,
    val current: Current
)

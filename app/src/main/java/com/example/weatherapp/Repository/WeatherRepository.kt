package com.example.weatherapp.Repository

import com.example.weatherapp.Server.ApiServices

class WeatherRepository (val api:ApiServices){
    fun getCurrentWeather(lat: Double,lng: Double, unit: String) =
        api.getCurrentWeather(lat,lng,unit,"0dfabc2738a78a7d3c74b95a4a39ef50")

    fun getForecastWeather(lat: Double,lng: Double, unit: String) =
        api.getForecastWeather(lat,lng,unit,"0dfabc2738a78a7d3c74b95a4a39ef50")
}
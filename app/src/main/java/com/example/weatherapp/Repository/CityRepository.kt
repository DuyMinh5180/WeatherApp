package com.example.weatherapp.Repository

import com.example.weatherapp.Server.ApiServices

class CityRepository(val api: ApiServices) {
    fun getCities(q:String,limit:Int) =
        api.getCitiesList(q,limit,"0dfabc2738a78a7d3c74b95a4a39ef50")
}
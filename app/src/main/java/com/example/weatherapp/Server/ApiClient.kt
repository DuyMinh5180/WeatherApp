package com.example.weatherapp.Server

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private lateinit var retrofit: Retrofit
    private val client = OkHttpClient.Builder() // Việc thiết lặp giúp cho ứng dụng
        // không bị chờ , treo trong quá trình tải dữ liệu
            .connectTimeout(60,TimeUnit.SECONDS) // thiết lập thời gian chờ kết nối
        .readTimeout(60,TimeUnit.SECONDS) // thiết lập thời gian chờ đọc dữ liệu
        .writeTimeout(60, TimeUnit.SECONDS) // thiết lập thời gian chờ ghi dữ liệu
        .build()

    fun getClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org") // Thiết lập url cơ sở cho api
            .client(client) // thiết lập client
            .addConverterFactory(GsonConverterFactory.create()) // thiết lập converter
            .build()
        return retrofit
    }
}
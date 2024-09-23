package com.example.weatherapp.Activity

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapter.CityAdapter
import com.example.weatherapp.Model.CityResponseApi
import com.example.weatherapp.ViewModel.CityViewModel
import com.example.weatherapp.databinding.ActivityCityListActivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCityListActivityBinding
    private val cityAdapter by lazy { CityAdapter() }
    private val cityViewModel: CityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            cityEdt.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    progressBar3.visibility = View.VISIBLE
                    cityViewModel.loadCity(p0.toString(),10).enqueue(object : Callback<CityResponseApi>{
                        override fun onResponse(
                            call: Call<CityResponseApi>,
                            response: Response<CityResponseApi>
                        ) {
                            if (response.isSuccessful) {
                                val data = response.body()
                                data?.let { cityList ->
                                    progressBar3.visibility = View.GONE
                                    cityAdapter.differ.submitList(cityList) // Cập nhật danh sách
                                    cityView.apply {
                                        layoutManager = LinearLayoutManager(this@CityListActivity, LinearLayoutManager.HORIZONTAL,false)
                                        adapter = cityAdapter
                                    }
                                }
                            }
                        }

                        override fun onFailure(call: Call<CityResponseApi>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
                }

            })
        }
    }
}
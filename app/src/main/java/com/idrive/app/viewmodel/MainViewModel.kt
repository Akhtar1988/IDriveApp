package com.idrive.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idrive.poc.remote.MainRepository
import com.idrive.app.model.TouristModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {
    val touristList = MutableLiveData<List<TouristModel>>()
    val error = MutableLiveData<String>()

    fun getAllTourist() {
        val response = repository.getAllTourist()
        response.enqueue(object : Callback<List<TouristModel>> {
            override fun onResponse(
                call: Call<List<TouristModel>>, response: Response<List<TouristModel>>
            ) {
                touristList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<TouristModel>>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }
}
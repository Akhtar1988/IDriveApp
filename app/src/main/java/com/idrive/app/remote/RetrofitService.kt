package com.idrive.poc.remote

import com.idrive.app.model.TouristModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitService {
    @GET("api/Tourist?page=1")
    fun getAllTourist(): Call<List<TouristModel>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://restapi.adequateshop.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
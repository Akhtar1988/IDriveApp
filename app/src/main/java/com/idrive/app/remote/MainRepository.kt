package com.idrive.poc.remote

class MainRepository constructor(private var retrofitService: RetrofitService) {

    fun getAllTourist() = retrofitService.getAllTourist()
}
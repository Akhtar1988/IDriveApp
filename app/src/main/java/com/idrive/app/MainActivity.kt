package com.idrive.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idrive.app.databinding.ActivityMainBinding
import com.idrive.app.viewmodel.MainViewModel
import com.idrive.app.viewmodel.MyViewModelFactory
import com.idrive.poc.adapter.MainAdapter
import com.idrive.poc.remote.MainRepository
import com.idrive.poc.remote.RetrofitService

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
    }
    private fun setAdapter() {
        viewModel = ViewModelProvider(this,MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.touristList.observe(this) {
            adapter = MainAdapter(it)
            val layoutManager = LinearLayoutManager(applicationContext)
            binding.recyclerView.layoutManager = layoutManager
            binding.recyclerView.adapter = adapter
        }
        viewModel.error.observe(this) {
            Log.d(TAG, it.toString())
        }
        viewModel.getAllTourist()
    }
}
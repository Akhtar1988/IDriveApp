package com.idrive.poc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idrive.app.databinding.ItemsRowBinding
import com.idrive.app.model.TouristModel

class MainAdapter(private val list: List<TouristModel>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemsRowBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = list[position]
        holder.binding.tvName.text = data.tourist_name
        holder.binding.tvEmail.text = data.tourist_email
        holder.binding.tvLocation.text = data.tourist_location
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MainViewHolder(val binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
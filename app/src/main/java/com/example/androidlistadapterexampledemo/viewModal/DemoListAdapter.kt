package com.example.androidlistadapterexampledemo.viewModal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlistadapterexampledemo.databinding.ListItemLayoutBinding
import com.example.androidlistadapterexampledemo.ui.Product
import com.example.androidlistadapterexampledemo.utils.loadImage


class DemoListAdapter :
    ListAdapter<Product, DemoListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(
                oldItem: Product,
                newItem: Product,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Product,
                newItem: Product,
            ): Boolean {
                return oldItem == newItem
            }
        }) {


    class ViewHolder(var binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.productNameTV.text = getItem(position).name
        holder.binding.imageIV.loadImage(getItem(position).imageUrl)

    }



}
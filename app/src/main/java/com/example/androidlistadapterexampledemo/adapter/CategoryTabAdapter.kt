package com.example.androidlistadapterexampledemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlistadapterexampledemo.databinding.ItemCategoryTabBinding

class CategoryTabAdapter(
    private val items: List<String>,
    private val onTabClick: (Int) -> Unit
) : RecyclerView.Adapter<CategoryTabAdapter.ViewHolder>() {

    var selectedIndex = 0

    inner class ViewHolder(val binding: ItemCategoryTabBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = items[position]
        val isSelected = position == selectedIndex

        holder.binding.categoryText.text = title
        holder.binding.indicator.visibility = if (isSelected) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            if (selectedIndex != position) {
                val old = selectedIndex
                selectedIndex = position
                notifyItemChanged(old)
                notifyItemChanged(position)
                onTabClick(position)
            }
        }
    }

    override fun getItemCount() = items.size
}

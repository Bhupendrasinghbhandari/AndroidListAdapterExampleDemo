package com.example.androidlistadapterexampledemo.viewModal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlistadapterexampledemo.databinding.CategoryItemLayoutBinding
import com.example.androidlistadapterexampledemo.utils.ProductListResponse
import com.example.androidlistadapterexampledemo.utils.gone
import com.example.androidlistadapterexampledemo.utils.invisible
import com.example.androidlistadapterexampledemo.utils.loadImage
import com.example.androidlistadapterexampledemo.utils.show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CategoryListAdapter :
    ListAdapter<ProductListResponse.CategoryData, CategoryListAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<ProductListResponse.CategoryData>() {
        override fun areItemsTheSame(
            oldItem: ProductListResponse.CategoryData, newItem: ProductListResponse.CategoryData
        ): Boolean {
            return oldItem.categoryId == newItem.categoryId
        }

        override fun areContentsTheSame(
            oldItem: ProductListResponse.CategoryData, newItem: ProductListResponse.CategoryData
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    var selectedIndex = 0
    var selectedItemCategoryID = -1
    var onCategoryClicked: ((ProductListResponse.CategoryData, Int) -> Unit)? = null

    inner class ViewHolder(val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onAttachedToRecyclerView(rv: RecyclerView) {
        super.onAttachedToRecyclerView(rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)
        val isSelected = position == selectedIndex
        if (isSelected) {
            selectedItemCategoryID = item.categoryId ?: -1
            holder.binding.categoryIndicator.show()
        } else {
            holder.binding.categoryIndicator.invisible()
        }
        holder.binding.weightMTV.text = item.categoryName
        holder.itemView.setOnClickListener {
            if (selectedIndex != position) {
                updateItemSelectedPosition(position)
                onCategoryClicked?.invoke(item, position)
            }
        }
        holder.binding.categoryIV.loadImage(item.icon)

    }

    fun updateItemSelectedPosition(position: Int) {
        val old = selectedIndex
        selectedIndex = position
        notifyItemChanged(old)
        notifyItemChanged(position)
    }


}

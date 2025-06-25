package com.example.androidlistadapterexampledemo.viewModal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlistadapterexampledemo.databinding.CategoryItemLayoutBinding
import com.example.androidlistadapterexampledemo.utils.ProductListResponse
import com.example.androidlistadapterexampledemo.utils.loadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoryListAdapter :
    ListAdapter<ProductListResponse.CategoryData, CategoryListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<ProductListResponse.CategoryData>() {
            override fun areItemsTheSame(
                oldItem: ProductListResponse.CategoryData,
                newItem: ProductListResponse.CategoryData,
            ): Boolean {
                return oldItem.categoryId.toString() == newItem.categoryId.toString()
            }

            override fun areContentsTheSame(
                oldItem: ProductListResponse.CategoryData,
                newItem: ProductListResponse.CategoryData,
            ): Boolean {
                return oldItem == newItem
            }
        }) {

    var isFirst = true

    var selectedPosition = 0

    var onCategoryClicked: ((ProductListResponse.CategoryData, Int) -> Unit?)? = null

    class ViewHolder(var binding: CategoryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (isFirst){
            isFirst=false
            onCategoryClicked?.invoke(getItem(position), position)
        }


        holder.binding.constraintCL.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onCategoryClicked?.invoke(getItem(position), position)
        }
        holder.binding.weightMTV.text = getItem(position).categoryName
        holder.binding.categoryIV.loadImage(getItem(position).icon)

    }

}
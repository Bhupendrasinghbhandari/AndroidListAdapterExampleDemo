package com.example.androidlistadapterexampledemo.viewModal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlistadapterexampledemo.databinding.ListItemLayoutBinding
import com.example.androidlistadapterexampledemo.utils.ProductListResponse.CategoryData.ProductVariantInfo
import com.example.androidlistadapterexampledemo.utils.loadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeProductAdapter : ListAdapter<ProductVariantInfo, HomeProductAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<ProductVariantInfo>() {

    override fun areItemsTheSame(
        oldItem: ProductVariantInfo,
        newItem: ProductVariantInfo,
    ): Boolean {
        return oldItem.id.toString() == newItem.id.toString()
    }

    override fun areContentsTheSame(
        oldItem: ProductVariantInfo,
        newItem: ProductVariantInfo,
    ): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }


  class ViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){


  }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.setIsRecyclable(false)
        val itemData = getItem(position)
//        holder.binding.imageIV.loadImage(itemData?.imageUrl?:"")
        val memberPrice = (itemData?.productInfo?.get(0)?.priceInfo?.memberPrice ?: 0).toString()
            .plus(itemData?.productInfo?.get(0)?.priceInfo?.membershipProductPriceTitle ?: "")
        val amount =
            (itemData?.productInfo?.get(0)?.priceInfo?.sellingPrice ?: 0).toString().plus("/")
                .plus(itemData?.productInfo?.get(0)?.displayUnit ?: "")
        holder.binding.productNameTV.text = itemData?.name
        holder.binding.weightMTV.text = itemData?.productInfo?.get(0)?.displayUnit ?: ""
        holder.binding.memberPriceTextMTV.text = memberPrice
        holder.binding.amountMTV.text = amount

    }
}
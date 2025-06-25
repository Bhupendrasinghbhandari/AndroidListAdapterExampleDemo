package com.example.androidlistadapterexampledemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidlistadapterexampledemo.ui.ProductFragment
import com.example.androidlistadapterexampledemo.utils.ProductListResponse

class ProductPagerAdapter(
    fa: FragmentActivity,
    private val categories: List<ProductListResponse.CategoryData>
) : FragmentStateAdapter(fa) {
    override fun getItemCount() = categories.size
    override fun createFragment(position: Int): Fragment {
        return ProductFragment.newInstance(categories[position])
    }
}

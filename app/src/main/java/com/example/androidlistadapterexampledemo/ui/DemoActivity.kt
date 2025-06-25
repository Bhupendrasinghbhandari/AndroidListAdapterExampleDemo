package com.example.androidlistadapterexampledemo.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.androidlistadapterexampledemo.R
import com.example.androidlistadapterexampledemo.adapter.CategoryTabAdapter
import com.example.androidlistadapterexampledemo.adapter.ProductPagerAdapter
import com.example.androidlistadapterexampledemo.databinding.ActivityDemoBinding
import com.example.androidlistadapterexampledemo.viewModal.DemoListAdapter

class DemoActivity : AppCompatActivity() {

    lateinit var binding: ActivityDemoBinding
    private val demoAdapter by lazy { DemoListAdapter() }
    private val demoAdapter1 by lazy { DemoListAdapter() }

    private lateinit var tabAdapter: CategoryTabAdapter
    private lateinit var pagerAdapter: ProductPagerAdapter

    private val categories =
        mutableListOf("Eggs", "Country", "Fruits", "Vegetables", "Pulses", "Breads", "Add-ons")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i in 1..100) {
            categories.add("item$i")
        }
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)
        tabAdapter = CategoryTabAdapter(categories) { position ->
            moveIndicatorToPosition(position)
            binding.productViewPager.setCurrentItem(position, true)
        }
        binding.categoryTabRV.layoutManager = LinearLayoutManager(this)
        binding.categoryTabRV.adapter = tabAdapter
//        pagerAdapter = ProductPagerAdapter(this, categories)
        binding.productViewPager.adapter = pagerAdapter
        binding.productViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        binding.productViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabAdapter.selectedIndex = position
                tabAdapter.notifyDataSetChanged()
//                moveIndicatorToPosition(position)
            }
        })


//        initRecyclerView()
//        initRecyclerView1()
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            binding.demoRV.smoothScrollToPosition(30)
//            binding.demoRV1.smoothScrollToPosition(100)
//        },2000)
    }

    private fun moveIndicatorToPosition(position: Int) {
        val layoutManager = binding.categoryTabRV.layoutManager as LinearLayoutManager
        val view = layoutManager.findViewByPosition(position)
//        view?.let {
//            val y = it.top.toFloat()
//            binding.categoryIndicator.animate()
//                .translationY(y)
//                .setDuration(300)
//                .start()
//        }
    }

//    private fun initRecyclerView1() {
//        binding.demoRV1.layoutManager = LinearLayoutManager(this)
//        binding.demoRV1.setHasFixedSize(true)
//        binding.demoRV1.adapter = demoAdapter1
//        val productList = (1..10000).map {
//            Product(
//                id = it,
//                name = "Product #$it",
//                imageUrl = "https://via.placeholder.com/150"
//            )
//        }
//
//        demoAdapter1.submitList(productList)
//    }
//
//    private fun initRecyclerView() {
//        binding.demoRV.layoutManager = LinearLayoutManager(this)
//        binding.demoRV.setHasFixedSize(true)
//        binding.demoRV.adapter = demoAdapter
//        val productList = (1..10000).map {
//            Product(
//                id = it,
//                name = "Product #$it",
//                imageUrl = "https://via.placeholder.com/150"
//            )
//        }
//
//        demoAdapter.submitList(productList)
//    }
}


data class Product(
    val id: Int,
    val name: String,
    val imageUrl: String
)

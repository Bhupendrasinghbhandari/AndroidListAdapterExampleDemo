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
import com.example.androidlistadapterexampledemo.R
import com.example.androidlistadapterexampledemo.databinding.ActivityDemoBinding
import com.example.androidlistadapterexampledemo.viewModal.DemoListAdapter

class DemoActivity : AppCompatActivity() {

    lateinit var binding: ActivityDemoBinding
    private val demoAdapter by lazy { DemoListAdapter() }
    private val demoAdapter1 by lazy { DemoListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)
        initRecyclerView()
        initRecyclerView1()

        Handler(Looper.getMainLooper()).postDelayed({
            binding.demoRV.smoothScrollToPosition(30)
            binding.demoRV1.smoothScrollToPosition(100)
        },2000)
    }

    private fun initRecyclerView1() {
        binding.demoRV1.layoutManager = LinearLayoutManager(this)
        binding.demoRV1.setHasFixedSize(true)
        binding.demoRV1.adapter = demoAdapter1
        val productList = (1..10000).map {
            Product(
                id = it,
                name = "Product #$it",
                imageUrl = "https://via.placeholder.com/150"
            )
        }

        demoAdapter1.submitList(productList)
    }

    private fun initRecyclerView() {
        binding.demoRV.layoutManager = LinearLayoutManager(this)
        binding.demoRV.setHasFixedSize(true)
        binding.demoRV.adapter = demoAdapter
        val productList = (1..10000).map {
            Product(
                id = it,
                name = "Product #$it",
                imageUrl = "https://via.placeholder.com/150"
            )
        }

        demoAdapter.submitList(productList)
    }
}



data class Product(
    val id: Int,
    val name: String,
    val imageUrl: String
)

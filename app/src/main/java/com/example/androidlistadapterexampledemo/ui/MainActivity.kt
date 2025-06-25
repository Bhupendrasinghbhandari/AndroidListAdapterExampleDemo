package com.example.androidlistadapterexampledemo.ui

import android.os.Bundle
import android.util.Log
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.datinggazm.utils.intentComponent
import com.example.androidlistadapterexampledemo.R
import com.example.androidlistadapterexampledemo.databinding.ActivityMainBinding
import com.example.androidlistadapterexampledemo.retrofit.handleResponse
import com.example.androidlistadapterexampledemo.utils.ProductListResponse
import com.example.androidlistadapterexampledemo.utils.ProductListResponse.CategoryData.ProductVariantInfo
import com.example.androidlistadapterexampledemo.utils.getCurrentFormattedDate
import com.example.androidlistadapterexampledemo.utils.show
import com.example.androidlistadapterexampledemo.viewModal.CategoryListAdapter
import com.example.androidlistadapterexampledemo.viewModal.HomeProductAdapter
import com.example.androidlistadapterexampledemo.viewModal.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("NAME_SHADOWING")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        intentComponent(DemoActivity::class.java, null)
        initAdapter()
        observer()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.currentDate = getCurrentFormattedDate()
            viewModel.getProductList()
        }
        onClickView()
    }


    private fun observer() {
        lifecycleScope.launch {
            viewModel.mutableLogInOrSignUpResponse.collectLatest {
                handleResponse(response = it, context = this@MainActivity, success = { response ->
                    if (!response.categoryDataList.isNullOrEmpty()) {
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.categoryList =
                                response.categoryDataList as ArrayList<ProductListResponse.CategoryData>
                            viewModel.categoryListAdapter.submitList(viewModel.categoryList)
                            filterAndHandleItem()
                        }
                    } else {
                        viewModel.categoryListAdapter.submitList(arrayListOf())
                    }
                }, error = { _, _ ->
                })
            }
        }
    }

    private fun filterAndHandleItem() {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in viewModel.categoryList.indices) {
                val productListItem =
                    viewModel.categoryList[i].productVariantInfo as ArrayList<ProductVariantInfo>
                for (j in productListItem.indices) {
                    viewModel.productList.add(productListItem[j])
                }
                withContext(Dispatchers.Main) {
                    viewModel.productAdapter.submitList(viewModel.productList)
                }
            }
        }
    }


    private fun onClickView() {
        binding.toolBarTB.backIV.setOnClickListener {
            finish()
        }
    }


    private fun initAdapter() {
        lifecycleScope.launch {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            viewModel.productAdapter = HomeProductAdapter()
            binding.recyclerRV.layoutManager = layoutManager
            binding.recyclerRV.setHasFixedSize(true)
            binding.recyclerRV.adapter = viewModel.productAdapter

            binding.recyclerRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisiblePos = layoutManager.findFirstVisibleItemPosition()

                    if (firstVisiblePos != RecyclerView.NO_POSITION) {
                        val currentProduct =
                            viewModel.productAdapter.currentList[firstVisiblePos].productInfo?.get(0)?.categoryId
                        handleScrollWhileUsingScroll(currentProduct)
                    }
                }
            })


            viewModel.categoryListAdapter = CategoryListAdapter()
            binding.categoryRV.adapter = viewModel.categoryListAdapter
            viewModel.categoryListAdapter.onCategoryClicked = { clickedItemData, position ->
                CoroutineScope(Dispatchers.IO).launch {
                    delay(1000)
                    clickedItemData.categoryId?.let { handleProductListScrollingBasedOnCategoryID(it) }
                }
                CoroutineScope(Dispatchers.Main).launch {

                    binding.categoryRV.post {
                        val layoutManager = binding.categoryRV.layoutManager as LinearLayoutManager
                        val view = layoutManager.findViewByPosition(position)
                        binding.categoryIndicator.show()
                        view?.let {
                            val targetY = it.top.toFloat()
                            binding.categoryIndicator.animate()
                                .translationY(targetY)
                                .translationY(targetY)
                                .setInterpolator(DecelerateInterpolator())
                                .setDuration(500)
                                .start()
                        }
                    }
                }

                binding.categoryRV.smoothScrollToPosition(position)
            }

            binding.categoryRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisiblePos = layoutManager.findFirstVisibleItemPosition()

                    if (firstVisiblePos != RecyclerView.NO_POSITION) {
                        CoroutineScope(Dispatchers.Main).launch {
                            binding.categoryRV.post {
                                val layoutManager =
                                    binding.categoryRV.layoutManager as LinearLayoutManager
                                val view = layoutManager.findViewByPosition(
                                    viewModel.categoryListAdapter.selectedPosition ?: 0
                                )
                                binding.categoryIndicator.show()
                                view?.let {
                                    val targetY = it.top.toFloat()
                                    binding.categoryIndicator.animate()
                                        .translationY(targetY)
                                        .translationY(targetY)
                                        .setInterpolator(DecelerateInterpolator())
                                        .setDuration(500)
                                        .start()
                                }
                            }
                        }
                        binding.categoryRV.smoothScrollToPosition(viewModel.categoryListAdapter.selectedPosition)

                    }
                }
            })
        }
    }


    private fun handleProductListScrollingBasedOnCategoryID(categoryId: Int) {
        var currentItemPosition = 0
        for (i in viewModel.productList.indices) {
            if (viewModel.productList[i].productInfo?.get(0)?.categoryId == categoryId) {
                currentItemPosition = i
                Log.i("MY_LOG_TAG",
                    "handleProductListScrollingBasedOnCategoryID: $currentItemPosition"
                )
                break
            }
        }
//        withContext(Dispatchers.Main) {
//            binding.recyclerRV.scrollToPosition(currentItemPosition)
//        }
    }

    fun handleScrollWhileUsingScroll(currentProduct: Int?) {
        var position = 0
        for (i in viewModel.categoryList.indices) {
            if (viewModel.categoryList[i].categoryId == currentProduct) {
                position = i
                break
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            binding.categoryRV.post {
                val layoutManager = binding.categoryRV.layoutManager as LinearLayoutManager
                val view = layoutManager.findViewByPosition(position)
                binding.categoryIndicator.show()
                view?.let {
                    val targetY = it.top.toFloat()
                    binding.categoryIndicator.animate()
                        .translationY(targetY)
                        .translationY(targetY)
                        .setInterpolator(DecelerateInterpolator())
                        .setDuration(500)
                        .start()
                }
            }
        }
        binding.categoryRV.smoothScrollToPosition(position)

    }

}
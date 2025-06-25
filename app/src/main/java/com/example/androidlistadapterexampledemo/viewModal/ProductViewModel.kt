package com.example.androidlistadapterexampledemo.viewModal

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.example.androidlistadapterexampledemo.base.ApiConstants
import com.example.androidlistadapterexampledemo.repo.DataSourceRepo
import com.example.androidlistadapterexampledemo.retrofit.ApiService
import com.example.androidlistadapterexampledemo.retrofit.Resource
import com.example.androidlistadapterexampledemo.utils.ProductListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val apiService: ApiService,
    private val baseRepo: DataSourceRepo,
) : BaseViewModel() {


    lateinit var productAdapter: HomeProductAdapter

    lateinit var categoryListAdapter: CategoryListAdapter

    var categoryList = ArrayList<ProductListResponse.CategoryData>()

    var productList = ArrayList<ProductListResponse.CategoryData.ProductVariantInfo>()

    fun getLikesListing(
        showStoryView: (String) -> Unit,
    ): kotlinx.coroutines.flow.Flow<PagingData<ProductListResponse.CategoryData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, enablePlaceholders = false
            ), pagingSourceFactory = {
                LikeListingDataSource(
                    apiService = apiService, showStoryView
                )
            }, initialKey = 0
        ).flow
    }


    val mutableLogInOrSignUpResponse by lazy { MutableSharedFlow<Resource<ProductListResponse>>() }


    var currentDate: String = ""

    private var pdpExperiment: String = "1"

    /**
     * this function is use to hit product listing  Api
     * [ApiConstants.PRODUCT_LISTING]
     * */
    suspend fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableLogInOrSignUpResponse.emit(Resource.LOADING())
            baseRepo.executeApi(
                apiType = ApiConstants.PRODUCT_LISTING
            ) {
                Log.i("MY_LOG_TAG", "getProductList: $currentDate \n  $pdpExperiment")
                apiService.getProductListing(currentDate, pdpExperiment)
            }.catch { exception ->
                mutableLogInOrSignUpResponse.emit(
                    Resource.Error(
                        exception.message.toString()
                    )
                )
            }.collect { isResponse ->
                Log.i("MY_LOG_TAG", "getProductList111: $isResponse")
                mutableLogInOrSignUpResponse.emit(isResponse)
            }
        }
    }

//    private val _allItems = MutableStateFlow<List<ProductListResponse.CategoryData.ProductVariantInfo>>(emptyList())
//
//    val pager = _allItems.flatMapLatest { allItems ->
//        Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = { InMemoryPagingSource(allItems) }
//        ).flow.cachedIn(viewModelScope)
//    }
//
//
//    fun setAllItems() {
//        _allItems.value = productList
//    }


    private val _visibleItems = MutableStateFlow(productList.take(10))
    private var pagingSource: InMemoryPagingSource? = null
    val pagerFlow = _visibleItems.flatMapLatest { visibleList ->
        Pager(
            config = PagingConfig(pageSize = 20), pagingSourceFactory = {
                InMemoryPagingSource(visibleList).also { pagingSource = it }
            }).flow.cachedIn(viewModelScope)
    }

    fun startAutoAppend() {
        viewModelScope.launch {
            while (_visibleItems.value.size < productList.size) {
//                delay(200)
                // add more every 3 seconds
                val currentSize = _visibleItems.value.size
                val nextChunk = productList.subList(
                    currentSize, (currentSize + 10).coerceAtMost(productList.size)
                )
                _visibleItems.value = _visibleItems.value.plus(nextChunk)
                pagingSource?.invalidate()
            }
        }
    }
}

class InMemoryPagingSource(
    private val items: List<ProductListResponse.CategoryData.ProductVariantInfo>
) : PagingSource<Int, ProductListResponse.CategoryData.ProductVariantInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListResponse.CategoryData.ProductVariantInfo> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        val fromIndex = page * pageSize
        val toIndex = (fromIndex + pageSize).coerceAtMost(items.size)

        val pageData = if (fromIndex < toIndex) {
            items.subList(fromIndex, toIndex)
        } else {
            emptyList()
        }

        return LoadResult.Page(
            data = pageData,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (toIndex < items.size) page + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ProductListResponse.CategoryData.ProductVariantInfo>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}


class LikeListingDataSource(
    private val apiService: ApiService,
    private val showStoryView: (String) -> Unit,
) : PagingSource<Int, ProductListResponse.CategoryData>() {

    override fun getRefreshKey(state: PagingState<Int, ProductListResponse.CategoryData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListResponse.CategoryData> {
        return try {

            val position = params.key ?: 0
            val hashMap = HashMap<String, Any>()
//            hashMap[ApiConstants.ApiParams.PAGE.value] = position.toString()
            val response = apiService.getProductListing(
                "", ""
            )

            val nextKey = if ((response.body()?.categoryDataList?.size ?: 0) < 10) {
                null
            } else position + 1
            LoadResult.Page(
                data = response.body()?.categoryDataList ?: arrayListOf(),
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
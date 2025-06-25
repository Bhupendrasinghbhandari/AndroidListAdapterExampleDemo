package com.example.androidlistadapterexampledemo.retrofit

import com.example.androidlistadapterexampledemo.base.ApiConstants
import com.example.androidlistadapterexampledemo.utils.ProductListResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {



    @GET(ApiConstants.PRODUCT_LISTING)
    suspend fun getProductListing(
        @Query("date") date: String,
        @Query("pdpExperiment") pdpExperiment: String
    ): Response<ProductListResponse>



}
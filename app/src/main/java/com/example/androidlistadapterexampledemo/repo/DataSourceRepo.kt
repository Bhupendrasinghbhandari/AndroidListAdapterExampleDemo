package com.example.androidlistadapterexampledemo.repo

import android.content.Context
import android.util.Log
import com.example.androidlistadapterexampledemo.retrofit.ApiService
import com.example.androidlistadapterexampledemo.retrofit.NetworkErrorHandlerImpl
import com.example.androidlistadapterexampledemo.retrofit.Resource
import com.example.androidlistadapterexampledemo.retrofit.responseToResourceFromServer
import com.example.androidlistadapterexampledemo.sharedPreference.SharedPrefs
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class DataSourceRepo @Inject constructor(
    private val apiService: ApiService,
    private val networkErrorHandlerImpl: NetworkErrorHandlerImpl,
    private val context: Context,
    private val sharedPrefs: SharedPrefs
) {
    suspend fun <T> executeApi(
        apiType: String,
        call: suspend () -> Response<T>
    ) = flow {
        try {
            val response = call()
            emit(
                responseToResourceFromServer(
                    response = response,
                    sharedPrefs = sharedPrefs,
                    context = context,
                    apiType = apiType
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("MY_loG_TAG", "executeApi: "+e.message)
            emit(
                Resource.Error(
                    networkErrorHandlerImpl.getErrorMessage(e),
                    apiType = apiType
                )
            )
        }
    }

}
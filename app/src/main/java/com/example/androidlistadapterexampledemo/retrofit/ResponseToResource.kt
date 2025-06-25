package com.example.androidlistadapterexampledemo.retrofit

import android.content.Context
import com.example.androidlistadapterexampledemo.R
import com.example.androidlistadapterexampledemo.sharedPreference.SharedPrefs
import org.json.JSONObject
import retrofit2.Response


/**
 * @param response
 * @param sharedPrefs
 * @param context
 * @param apiType
 * @param T <T> is the type of response
 * this function is use to convert api response into resource. it will help to filter api response according to response code
 * */

fun <T> responseToResourceFromServer(
    response: Response<T>,
    sharedPrefs: SharedPrefs,
    context: Context,
    apiType: String
): Resource<T> {
    if (response.isSuccessful && response.code() == 201) {
        response.body()?.let { result ->
            return Resource.Success(result)
        }
    } else if (response.code() == 401) {
//        sharedPrefs.clearPreference()
//        context.intentComponentWithNewTask(LogInActivity::class.java, null)
        return Resource.Error(
            context.getString(R.string.your_account_login),
            apiType = apiType
        )
    } else if (response.code() == 400) {
        val json = JSONObject(response.errorBody()!!.string())
        if (json.has(messageKey)) {
            return Resource.Error(
                json.get(messageKey) as String,
                apiType = "",
                responseCode = response.code().toString()
            )
        }
    } else if (response.code() == 500) {
        return Resource.Error(
            context.getString(R.string.timeoutErr),
            apiType = apiType,
            responseCode = response.code().toString()
        )
    } else if (response.code() == 200) {
        response.body()?.let { result ->
            return Resource.Success(result)
        }
    }
    return Resource.Error(
        response.message(),
        apiType = apiType,
        responseCode = response.code().toString()
    )
}


const val messageKey = "message"
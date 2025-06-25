package com.example.androidlistadapterexampledemo.retrofit

import android.app.Activity
import com.example.androidlistadapterexampledemo.utils.ProgressDialog
import com.example.androidlistadapterexampledemo.utils.showToast

/**
 * Handling the parsed data from the
 * @param context
 * @param success calling success lambda if success
 * @param error calling error lambda if getting any error or failure
 * */

fun <T> handleResponse(
    response: Resource<T>,
    context: Activity,
    success: (response: T) -> Unit,
    showToast: Boolean = true,
    isLoading: Boolean = true,
    error: (message: String, apiName: String) -> Unit,
) {
    when (response) {
        is Resource.Success -> {
            ProgressDialog.hideProgress()
            response.data?.let { success.invoke(it) }
        }
        is Resource.Error -> {
            ProgressDialog.hideProgress()
            error.invoke(response.message ?: "", response.apiType)
            response.message?.let { message ->
                if (showToast) context.showToast(
                    message = message
                )
            }
        }
        is Resource.LOADING -> {
            if (isLoading) {
                ProgressDialog.showProgress(context)
            }
        }
    }
}
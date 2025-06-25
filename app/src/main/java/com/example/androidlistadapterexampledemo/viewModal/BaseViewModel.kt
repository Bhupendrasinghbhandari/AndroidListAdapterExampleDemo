package com.example.androidlistadapterexampledemo.viewModal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [BaseViewModel]
 * this class is use to perform some common task that is similar for all View Modals
 * */

open class BaseViewModel : ViewModel() {

    var param = HashMap<String, Any>()
    val onErrorToast = MutableLiveData<String>()
    var showLoading = MutableStateFlow(false)

    private var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        if (job != null) {
            job?.cancel()
        }
    }


}

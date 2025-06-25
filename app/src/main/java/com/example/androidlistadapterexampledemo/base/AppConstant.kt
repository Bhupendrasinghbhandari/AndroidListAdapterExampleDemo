package com.example.androidlistadapterexampledemo.base

/**
 * [AppConstant]
 * this class contains All Constant variable of the app
 * In Kotlin you also have the #object keyword.
 * It is used to obtain a data type with a single implementation.
 * If you are a Java user and want to understand what "single" means, you can think of the Singleton pattern:
 * it ensures you that only one instance of that class is created even if 2 threads try to create it.
 * */

object AppConstant {

    const val TOKEN = "TOKEN"
    const val MY_LOG_TAG = "MY_LOG_TAG"
    const val BASE_URL = "https://qa-cms.countrydelight.in/api/v4/"


}
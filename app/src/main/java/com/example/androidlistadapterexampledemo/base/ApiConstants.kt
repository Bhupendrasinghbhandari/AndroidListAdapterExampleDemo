package com.example.androidlistadapterexampledemo.base


/**
 * [ApiConstants]
 *
 * this class contains All Constant variable of the app
 * In Kotlin you also have the #object keyword.
 * It is used to obtain a data type with a single implementation.
 * If you are a Java user and want to understand what "single" means, you can think of the Singleton pattern:
 * it ensures you that only one instance of that class is created even if 2 threads try to create it.
 *
 * */


object ApiConstants {

    const val PROFILE_BASE_URL =
        "https://php.parastechnologies.in/encro/storage/app/public/uploads/users/"


    const val PRODUCT_LISTING = "customer/products"

    const val BLOCKED_LIST = "blocked/list"

    /**
     * APi Params
     * *****************************************************************************************************************************************************
     * */
    enum class ApiParams(val value: String) {
        EMAIL("email"),
        AMOUNT("amount"),
    }

}




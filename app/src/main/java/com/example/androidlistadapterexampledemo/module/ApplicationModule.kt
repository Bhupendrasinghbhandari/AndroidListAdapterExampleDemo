package com.example.androidlistadapterexampledemo.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.androidlistadapterexampledemo.base.AppConstant
import com.example.androidlistadapterexampledemo.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 *
 * [ApplicationModule]
 * Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
 * Doing manual dependency injection requires you to construct every class and its dependencies by hand,
 * and to use containers to reuse and manage dependencies.
 *
 * Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically.
 * Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness,
 * runtime performance, scalability, and Android Studio support that Dagger provides.
 * For more information, see Hilt and Dagger.
 *
 * */

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    /**
     * it will provide the context where ever we want on our project
     * */

    @Provides
    @Singleton
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }


    /**
     * it will provide  the [OkHttpClient]
     * @param interceptor
     * */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        interceptor: Interceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).addInterceptor(interceptor)
            .addInterceptor(
                interceptor = ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context)).maxContentLength(250000L)
                    .redactHeaders(emptySet()).alwaysReadResponseBody(false).build()
            ).build()
    }


    /**
     * this function is use to provide the [Interceptor] for [provideOkHttpClient]
     *
     * */

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
            val token = "7432c8c3-0bac-40f3-9b4d-ce4a0a00789dgt-cd"
            if (token.isEmpty().not()) {
                request.header(
                    "Authorization", token
                ).addHeader("Accept", "application/json").build()
            }
            request.build()
            chain.proceed(request.build())
        }
    }

    /**
     * this method will bind the [Retrofit] object with [OkHttpClient]
     * @return [Retrofit]
     * */

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient, BASE_URL: String
    ): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .client(okHttpClient).build()

    /**
     * this function will provide the [SharedPreferences] object where ever we want
     * @return [Application]
     * */

    @Provides
    @Singleton
    fun providerSharedPref(activity: Application): SharedPreferences {
        return (activity as Context).getSharedPreferences("androidlistadapterexampledemo", Context.MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return AppConstant.BASE_URL
    }

}






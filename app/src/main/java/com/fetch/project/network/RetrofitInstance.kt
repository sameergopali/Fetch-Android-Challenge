package com.fetch.project.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to provide a Retrofit instance configured with a base URL and a Gson converter.
 */
object RetrofitInstance {
    /**
     * Lazy-initialized Retrofit instance.
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Lazy-initialized [ApiService] instance.
     */
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
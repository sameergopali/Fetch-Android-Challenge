package com.fetch.project.network

import mockwebserver3.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlinx.coroutines.test.runTest

class APITest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockRequestDispatcher()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/test/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @Test
    fun getItemsTest() = runTest {
        try {
            val response = apiService.getItems()
            assert(response.isSuccessful)
            assert(response.body()!!.isNotEmpty())
        }
        catch (e: Exception){
            println(e.message)
        }
    }


    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
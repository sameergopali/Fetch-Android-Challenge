package com.fetch.project.network

import com.fetch.project.model.Item
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface representing the API service for fetching items.
 */
interface ApiService {
    /**
     * Fetches the list of items from the server.
     *
     * @return A [Response] containing a list of [Item] objects.
     */
    @GET("hiring.json")
    suspend fun getItems(): Response<List<Item>>
}
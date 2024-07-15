package com.fetch.project.repository

import com.fetch.project.model.Item
import com.fetch.project.network.NetworkResult
import com.fetch.project.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository implementation for fetching items.
 */
class ItemRepository: IRepository {

    /**
     * Fetches the list of items from the API.
     *
     * @return A [NetworkResult] containing a list of [Item] objects.
     */
    override  suspend fun getItems(): NetworkResult<List<Item>> {
        return withContext(Dispatchers.IO){
            try {
                // Fetch items from the API
                val response = RetrofitInstance.api.getItems()

                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                }
                else{
                    NetworkResult.Error(response.errorBody().toString())
                }
            }  catch(e: Exception){
                    NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}
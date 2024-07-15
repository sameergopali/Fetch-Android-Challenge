package com.fetch.project.repository

import com.fetch.project.model.Item
import com.fetch.project.network.NetworkResult

/**
 * Interface representing a repository for fetching items.
 */
interface IRepository {
    /**
     * Fetches the list of items from the repository.
     *
     * @return A [NetworkResult] containing a list of [Item] objects.
     */
    suspend  fun  getItems(): NetworkResult<List<Item>>
}
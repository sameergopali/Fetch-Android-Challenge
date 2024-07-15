package com.fetch.project.viewmodel

import com.fetch.project.model.Item

/**
 * Data class representing the UI state for items.
 *
 * @property isLoading Indicates if the data is currently being loaded.
 * @property error An error message if an error occurred, null otherwise.
 * @property data The list of items to be displayed.
 */
data class ItemUIState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<Item> = emptyList()
)
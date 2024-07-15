package com.fetch.project.model

/**
 * Data class representing an item.
 *
 * @property id The unique identifier for the item.
 * @property listId The identifier for the list to which the item belongs.
 * @property name The name of the item. This can be null.
 */
data class Item (
    val id :Int,
    val listId: Int,
    val name: String?
)

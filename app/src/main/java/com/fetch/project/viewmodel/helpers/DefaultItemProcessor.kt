package com.fetch.project.viewmodel.helpers

import com.fetch.project.model.Item

/**
 * Default implementation for [ItemProcessor] for processing items.
 **/
object DefaultItemProcessor: ItemProcessor {
    /**
     * Processes the given list of items.
     *
     * @param data The list of items to be processed.
     * @return A list of processed items.
     **/
    override fun process(data: List<Item>): List<Item> {
        // Filter out items with empty names and sort them based on listId and name.
        return data.filter { it.name?.isNotBlank() == true }
            .sortedWith(compareBy({ it.listId }, { it.name }))
    }
}
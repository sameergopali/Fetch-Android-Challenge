package com.fetch.project.viewmodel.helpers

import com.fetch.project.model.Item

/*
 * Interface for processing items.
 */
interface ItemProcessor {
    /**
    * Processes the given list of items.
    *
    * @param data The list of items to be processed.
     * @return A list of processed items.
     **/
    fun process(data: List<Item>): List<Item>

}
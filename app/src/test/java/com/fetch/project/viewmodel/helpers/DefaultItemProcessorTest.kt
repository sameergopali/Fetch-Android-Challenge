package com.fetch.project.viewmodel.helpers

import com.fetch.project.model.Item
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DefaultItemProcessorTest {
    private lateinit var items: List<Item>
    private lateinit var expectedFiltered: List<Item>
    @Before
    fun setUp() {
        items = listOf(
            Item(1, 1, ""),
            Item(3, 3, "Item 3"),
            Item(2, 2, "Item 2"),
            Item(4, 1, "Item 1"),
            Item(5, 2, "Item 5")
        )

        // Expected filtered items
         expectedFiltered = listOf(
             Item(4, 1, "Item 1"),
             Item(2, 2, "Item 2"),
             Item(5, 2, "Item 5"),
             Item(3, 3, "Item 3"),

         )
    }


    @Test
    fun process() {
        val filterItems = DefaultItemProcessor.process(items)
        assertEquals(filterItems, expectedFiltered)
    }
}
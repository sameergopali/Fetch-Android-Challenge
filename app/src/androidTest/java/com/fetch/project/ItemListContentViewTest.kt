package com.fetch.project

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.fetch.project.model.Item
import com.fetch.project.ui.view.components.ItemListContentView
import org.junit.Rule
import org.junit.Test

class ItemListContentViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_listItemContentView() {

        with(composeTestRule) {
            setContent {
                ItemListContentView(
                    data = listOf(Item(1, 1, "Item 1"))
                )
            }
            onNodeWithTag("Header").assertExists()
            onNodeWithTag("ItemCard").assertExists()
        }
    }

}
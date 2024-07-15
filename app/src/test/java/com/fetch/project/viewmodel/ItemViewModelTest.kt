package com.fetch.project.viewmodel

import com.fetch.project.model.Item
import com.fetch.project.network.NetworkResult
import com.fetch.project.repository.ItemRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ItemViewModelTest{
    private val repository = mockk<ItemRepository>(relaxed = true)
    private lateinit var  itemViewModel: ItemViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        itemViewModel = ItemViewModel(repository)
    }

    @Test
    fun  getItems_success() = runTest {
        val items = listOf(
            Item(4, 1, "Item 1"),
            Item(2, 2, "Item 2"),
            Item(5, 2, "Item 5"),
            Item(3, 3, "Item 3"),
        )

        coEvery { repository.getItems() } returns NetworkResult.Success(items)
        itemViewModel.getItems()
        assertEquals(itemViewModel.itemUIState.value.data, items)
        assertEquals(itemViewModel.itemUIState.value.isLoading, false)
        assertEquals(itemViewModel.itemUIState.value.error, null)

    }

    @Test
    fun getItems_networkError() = runTest {
        val errorMessage = "Network Error"
        coEvery { repository.getItems() } returns NetworkResult.Error(errorMessage)

        itemViewModel.getItems()

        val uiState = itemViewModel.itemUIState.value
        assertEquals(emptyList<Item>(),uiState.data)
        assertFalse("Loading state should be false after error",uiState.isLoading)
        assertEquals(
            errorMessage,
            uiState.error
        )
    }

    @Test
    fun getItems_loadingState() = runTest {
        val items = listOf(
            Item(4, 1, "Item 1")
        )
        // Simulate a delay in the repository response to test the loading state
        coEvery { repository.getItems() } coAnswers {
            delay(100)
            NetworkResult.Success(items)
        }

        itemViewModel.getItems()
        // Immediately after calling getItems, the state should be loading
        assertTrue(
            "Loading state should be true initially",
            itemViewModel.itemUIState.value.isLoading
        )
    }


}
package com.fetch.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.project.model.Item
import com.fetch.project.network.NetworkResult
import com.fetch.project.repository.ItemRepository
import com.fetch.project.viewmodel.helpers.DefaultItemProcessor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the UI state of items.
 *
 * @property itemRepository The repository for fetching items.
 */
class ItemViewModel(private val itemRepository: ItemRepository): ViewModel() {
    private val _itemUIState = MutableStateFlow(ItemUIState())
    val itemUIState = _itemUIState.asStateFlow()
    private val itemProcessor = DefaultItemProcessor
    init {
        getItems()
    }


    /**
     * Fetches items from the repository and updates the UI state.
     */
    fun getItems() {
        _itemUIState.value = ItemUIState(isLoading = true)
        viewModelScope.launch {
            val result = itemRepository.getItems()
            handleResult(result)
        }
    }

    /**
     * Handles the result of the network request.
     *
     * @param result The result of fetching items from the repository.
     */
    private fun handleResult(result: NetworkResult<List<Item>>) {
        when (result) {
            is NetworkResult.Success -> handleSuccess(result.data)
            is NetworkResult.Error -> handleError(result.message)
        }
    }

    /**
     * Handles a successful result of fetching items.
     *
     * @param data The list of items. The items are filtered if they
     * have a non-empty name and sorted based on listId and name.
     */
    private fun handleSuccess(data: List<Item>) {
        val filteredAndSorted = itemProcessor.process(data)
        _itemUIState.update {
            it.copy(
                isLoading = false,
                data = filteredAndSorted,
                error = null
            )
        }
    }

    /**
     * Handles an error result of fetching items.
     *
     * @param errorMessage The error message from the network request.
     */
    private fun handleError(errorMessage: String?) {
        _itemUIState.update {
            it.copy(
                isLoading = false,
                error = errorMessage
            )
        }
    }
}




/**
 * Singleton object for providing an instance of [ItemViewModel].
 */
object ItemViewModelSingleton {
    private val viewModel by lazy { ItemViewModel(ItemRepository()) }
    /**
     * The lazy-initialized instance of [ItemViewModel].
     *
     * @return The singleton instance of [ItemViewModel]
     */
    fun getInstance(): ItemViewModel = viewModel
}
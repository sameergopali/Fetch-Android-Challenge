package com.fetch.project.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fetch.project.ui.view.components.ErrorView
import com.fetch.project.ui.view.components.ItemListContentView
import com.fetch.project.ui.view.components.LoadingIndicatorView
import com.fetch.project.viewmodel.ItemViewModel
import com.fetch.project.viewmodel.ItemViewModelSingleton

/**
 * Composable function to display the list of items. Displays a
 * loading view or item list view, or an error view based on the
 * itemUIState.
 *
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun ItemListView(modifier: Modifier) {
    // Get the ItemViewModel instance
    val itemViewModel: ItemViewModel = ItemViewModelSingleton.getInstance()
    // Collect the UI state from the ItemViewModel
    val itemUIState by itemViewModel.itemUIState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            // Display the loading indicator when data is being fetched.
            LoadingIndicatorView(visible = itemUIState.isLoading)
            // Display the item list when data is available.
            ItemListContentView(data = itemUIState.data)
            // Display if error message when there is an problem while fetching data.
            ErrorView(error = itemUIState.error, onRetry = { itemViewModel.getItems() })
    }
}







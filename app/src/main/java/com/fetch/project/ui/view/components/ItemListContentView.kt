package com.fetch.project.ui.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.fetch.project.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable function to display the content view of the item list.
 * Groups items by their list ID and allows scrolling between groups.
 *
 * @param data The list of items to be displayed.
 * @param modifier The modifier to be applied to the composable.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemListContentView(data: List<Item>, modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyListState() // Add a state for the LazyColumn
    val coroutineScope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = data.isNotEmpty(),
        modifier = modifier
    ) {
        val grouped = data.groupBy { it.listId }

        LazyColumn(state = lazyListState) {
            grouped.forEach { (listId, items) ->
                val index = grouped.keys.indexOf(listId)
                val isFirst = index == 0
                val isLast = index == grouped.keys.size - 1
                stickyHeader {
                    HeaderWithButton(
                        listId = listId,
                        showPrevious = !isFirst, // Show the previous group button if not the first group
                        showNext = !isLast, // Show the next group button if not the last group
                        onPreviousClick = { scrollToGroup(grouped, listId, -1, coroutineScope, lazyListState, data) },
                        onNextClick = { scrollToGroup(grouped, listId, 1, coroutineScope, lazyListState, data) }
                    )
                }
                items(items) { item ->
                    ItemCard(item = item)
                }
            }
        }
    }
}


/**
 * Scrolls to a specific group in the LazyColumn.
 *
 * @param grouped The map of grouped items by list ID.
 * @param currentListId The current list ID being viewed.
 * @param direction The direction to scroll (-1 for previous, 1 for next).
 * @param coroutineScope The coroutine scope for launching the scroll operation.
 * @param lazyListState The state of the LazyColumn.
 * @param data The list of items to be displayed.
 */
private fun scrollToGroup(
    grouped: Map<Int, List<Item>>,
    currentListId: Int,
    direction: Int,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState,
    data: List<Item>
) {
    // Get the index of the current list ID in the grouped map
    val currentIndex = grouped.keys.indexOf(currentListId)
    // The target index is the current index plus the direction(-1 for previous, 1 for next)
    val targetIndex = currentIndex + direction

    // Check if the target index is within the valid range
    if (targetIndex in 0 until grouped.size) {
        // Launch a coroutine to scroll to the target index
        coroutineScope.launch {
            val targetListId = grouped.keys.elementAt(targetIndex)
            // Get the index of the first item in the target list
            val index = data.indexOf(grouped[targetListId]?.first())
            // Animate the scroll to the target index
            lazyListState.animateScrollToItem(index = index, scrollOffset = if (direction < 0) 1 else 0)
        }
    }
}


/**
 * Composable function to display a sticky header with navigation
 * buttons for scrolling between groups.
 *
 * @param listId The ID of the current list being displayed.
 * @param showPrevious Whether to show the button to navigate to the previous group.
 * @param showNext Whether to show the button to navigate to the next group.
 * @param onNextClick The action to perform when the "next" button is clicked.
 * @param onPreviousClick The action to perform when the "previous" button is clicked.
 */
@Composable
fun HeaderWithButton(listId: Int,
                     showPrevious: Boolean,
                     showNext: Boolean,
                     onNextClick: () -> Unit,
                     onPreviousClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary)
            .testTag("Header")
    ) {
        Header(text = "List ID: $listId")
        Row (modifier = Modifier.align(Alignment.CenterEnd)) {
            // Add the previous group buttons to the header
            if (showPrevious) {
                IconButton(onClick = onPreviousClick) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Previous Group"
                    )
                }
            }
            // Add the next group buttons to the header
            if (showNext) {
                IconButton(onClick = onNextClick) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Next Group"
                    )
                }
            }

        }
    }

}


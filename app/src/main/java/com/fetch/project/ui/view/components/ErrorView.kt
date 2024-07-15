package com.fetch.project.ui.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 *  Composable function to display the error message
 *
 *  @param error The error message to be displayed
 *  @param onRetry The callback function to be invoked when the retry button is clicked
 */
@Composable
fun ErrorView(error: String?, onRetry: () -> Unit) {
    AnimatedVisibility(visible = error != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Error!! Could not fetch data",
                    color = MaterialTheme.colorScheme.error
                )
                IconButton(
                    onClick = onRetry
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Reload",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}

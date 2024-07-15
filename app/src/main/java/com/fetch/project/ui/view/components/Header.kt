package com.fetch.project.ui.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function for displaying a header text.
 *
 * @param text The text to be displayed as the header.
 */
@Composable
fun Header(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSecondary,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

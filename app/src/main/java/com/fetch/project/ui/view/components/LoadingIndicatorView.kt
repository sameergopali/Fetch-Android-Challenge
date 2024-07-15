package com.fetch.project.ui.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Composable function to display a loading indicator.
 *
 * @param visible Indicates whether to display the loading indicator or not.
 */
@Composable
fun LoadingIndicatorView(visible: Boolean) {
    AnimatedVisibility(visible = visible) {
        LinearProgressIndicator(
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

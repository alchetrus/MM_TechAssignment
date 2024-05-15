package com.mm.newsreader.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AppLoadingWrapper(
    modifier: Modifier,
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    if (!isLoading) {
        content()
    } else {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AppCircularProgress(modifier = modifier)
        }
    }
}
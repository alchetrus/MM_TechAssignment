package com.mm.newsreader.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.mm.newsreader.R

@Composable
fun AppCircularProgress(
    modifier: Modifier
) {
    CircularProgressIndicator(
        modifier = modifier.size(dimensionResource(id = R.dimen.size_big)),
        color = MaterialTheme.colorScheme.secondary,
        strokeWidth = dimensionResource(id = R.dimen.stroke_x_small)
    )
}
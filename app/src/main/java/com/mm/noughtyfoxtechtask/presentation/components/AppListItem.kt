package com.mm.noughtyfoxtechtask.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mm.noughtyfoxtechtask.R

@Composable
fun AppListItem(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.padding_x_small)),
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_round_small))
    ) {
        Box(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {
            content()
        }
    }
}

@Preview
@Composable
private fun PreviewAppListItem() {
    AppListItem(modifier = Modifier) {
        Text(text = "Text")
    }
}
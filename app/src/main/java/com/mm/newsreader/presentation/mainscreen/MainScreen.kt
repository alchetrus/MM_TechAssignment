package com.mm.newsreader.presentation.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mm.newsreader.R
import com.mm.newsreader.data.model.Article
import com.mm.newsreader.presentation.components.AppListItem
import com.mm.newsreader.presentation.components.AppLoadingWrapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun MainScreenRoute(
    viewModel: MainScreenViewModel = hiltViewModel(),
    onItemClick: (itemId: String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    MainScreen(
        onItemClick = onItemClick,
        newsArticles = uiState.value.newsArticles,
        isLoading = uiState.value.isLoading,
        errorChannel = viewModel.errorChannel,
        onRetryClick = viewModel::getNewsArticles
    )
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    newsArticles: List<Article>,
    isLoading: Boolean,
    errorChannel: Channel<String>,
    onItemClick: (itemId: String) -> Unit,
    onRetryClick: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarActionLabel = stringResource(id = R.string.retry_text)
    LaunchedEffect(key1 = errorChannel) {
        launch {
            errorChannel.receiveAsFlow().collect {
                val snackBarResult = snackBarHostState.showSnackbar(
                    it,
                    actionLabel = snackBarActionLabel
                )

                when (snackBarResult) {
                    SnackbarResult.ActionPerformed -> onRetryClick()
                    SnackbarResult.Dismissed -> {}
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AppLoadingWrapper(modifier = modifier, isLoading = isLoading) {
                LazyColumn(
                    contentPadding = PaddingValues(
                        horizontal = dimensionResource(id = R.dimen.padding_medium)
                    )
                ) {
                    items(items = newsArticles, key = { article -> article.uuid }) {
                        AppListItem(modifier = modifier
                            .clickable {
                                onItemClick(it.uuid)
                            }) {
                            Column {
                                Text(text = it.title)
                                Text(text = it.publishedAt)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen(
        onItemClick = {},
        newsArticles = emptyList(),
        isLoading = false,
        errorChannel = Channel<String>(),
        onRetryClick = {})
}
package com.mm.newsreader.presentation.detailsscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mm.newsreader.R
import com.mm.newsreader.data.model.Article
import com.mm.newsreader.presentation.components.AppLoadingWrapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun DetailsScreenRoute(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    itemId: String?,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = itemId) {
        if (itemId != null) {
            viewModel.getArticle(itemId)
        }
    }

    DetailsScreen(
        onNavigateBack = onNavigateBack,
        newsArticle = uiState.newsArticle,
        isLoading = uiState.isLoading,
        errorChannel = viewModel.errorChannel,
        onRetryClick = {
            if (itemId != null) {
                viewModel.getArticle(itemId)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreen(
    modifier: Modifier = Modifier,
    newsArticle: Article,
    isLoading: Boolean,
    errorChannel: Channel<String>,
    onNavigateBack: () -> Unit,
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
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back_text)
                        )
                    }
                },
                title = {}
            )
        }
    ) { paddingValues ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AppLoadingWrapper(modifier = modifier, isLoading = isLoading) {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Column(modifier = modifier.fillMaxWidth()) {
                        Text(text = newsArticle.title)
                        Text(text = newsArticle.description)
                    }
                }
            }
        }
    }
}
package com.mm.newsreader.presentation.mainscreen

import com.mm.newsreader.data.model.Article
import javax.annotation.concurrent.Immutable

@Immutable
data class MainScreenState(
    val newsArticles: List<Article> = emptyList(),
    val isLoading: Boolean = false
)

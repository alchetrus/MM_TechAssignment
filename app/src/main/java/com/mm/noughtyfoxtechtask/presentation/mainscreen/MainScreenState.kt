package com.mm.noughtyfoxtechtask.presentation.mainscreen

import com.mm.noughtyfoxtechtask.data.model.Article
import javax.annotation.concurrent.Immutable

@Immutable
data class MainScreenState(
    val newsArticles: List<Article> = emptyList(),
    val isLoading: Boolean = false
)

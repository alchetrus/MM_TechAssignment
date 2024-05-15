package com.mm.newsreader.presentation.detailsscreen

import com.mm.newsreader.data.model.Article
import javax.annotation.concurrent.Immutable

@Immutable
data class DetailsScreenState(
    val newsArticle: Article = Article(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        emptyList()
    ),
    val isLoading: Boolean = false
)

package com.mm.noughtyfoxtechtask.presentation.detailsscreen

import com.mm.noughtyfoxtechtask.data.model.Article
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

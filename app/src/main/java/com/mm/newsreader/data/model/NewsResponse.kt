package com.mm.newsreader.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val meta: Meta,
    val data: List<Article>
)

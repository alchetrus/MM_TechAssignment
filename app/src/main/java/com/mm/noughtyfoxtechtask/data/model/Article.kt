package com.mm.noughtyfoxtechtask.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val uuid: String,
    val title: String,
    val description: String,
    val keywords: String,
    val snippet: String,
    val url: String,
    @SerialName("image_url") val imageUrl: String,
    val language: String,
    @SerialName("published_at") val publishedAt: String,
    val source: String,
    val categories: List<String>,
)

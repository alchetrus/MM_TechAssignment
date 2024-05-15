package com.mm.newsreader.data.repository

import com.mm.newsreader.data.model.Article
import com.mm.newsreader.data.repository.utils.Languages
import com.mm.newsreader.data.repository.utils.NetworkResult
import com.mm.newsreader.data.model.NewsResponse

interface ApiRepository {
    suspend fun getAllNews(languages: Languages): NetworkResult<NewsResponse>

    suspend fun getArticle(articleUuid: String): NetworkResult<Article>
}
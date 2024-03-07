package com.mm.noughtyfoxtechtask.data.repository

import com.mm.noughtyfoxtechtask.data.model.Article
import com.mm.noughtyfoxtechtask.data.repository.utils.Languages
import com.mm.noughtyfoxtechtask.data.repository.utils.NetworkResult
import com.mm.noughtyfoxtechtask.data.model.NewsResponse

interface ApiRepository {
    suspend fun getAllNews(languages: Languages): NetworkResult<NewsResponse>

    suspend fun getArticle(articleUuid: String): NetworkResult<Article>
}
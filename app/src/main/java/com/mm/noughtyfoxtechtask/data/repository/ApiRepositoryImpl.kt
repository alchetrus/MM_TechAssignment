package com.mm.noughtyfoxtechtask.data.repository

import com.mm.noughtyfoxtechtask.BuildConfig
import com.mm.noughtyfoxtechtask.data.model.Article
import com.mm.noughtyfoxtechtask.data.remote.api.ApiService
import com.mm.noughtyfoxtechtask.data.repository.utils.Languages
import com.mm.noughtyfoxtechtask.data.repository.utils.NetworkResult
import com.mm.noughtyfoxtechtask.data.repository.utils.handleApiResponse
import com.mm.noughtyfoxtechtask.data.model.NewsResponse
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ApiRepository {

    private val apiKey = BuildConfig.apikey
    override suspend fun getAllNews(languages: Languages): NetworkResult<NewsResponse> =
        handleApiResponse {
            apiService.getAllNews(
                apiToken = apiKey, language = languages.name.lowercase()
            )
        }

    override suspend fun getArticle(articleUuid: String): NetworkResult<Article> =
        handleApiResponse { apiService.getNews(newsId = articleUuid, apiToken = apiKey) }
}
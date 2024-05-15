package com.mm.newsreader.data.repository

import com.mm.newsreader.BuildConfig
import com.mm.newsreader.data.model.Article
import com.mm.newsreader.data.remote.api.ApiService
import com.mm.newsreader.data.repository.utils.Languages
import com.mm.newsreader.data.repository.utils.NetworkResult
import com.mm.newsreader.data.repository.utils.handleApiResponse
import com.mm.newsreader.data.model.NewsResponse
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
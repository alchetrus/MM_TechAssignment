package com.mm.newsreader.data.remote.api

import com.mm.newsreader.data.model.Article
import com.mm.newsreader.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("news/all")
    suspend fun getAllNews(
        @Query("api_token") apiToken: String,
        @Query("language") language: String
    ): Response<NewsResponse>

    @GET("news/uuid/{uuid}")
    suspend fun getNews(
        @Path("uuid") newsId: String,
        @Query("api_token") apiToken: String
    ): Response<Article>
}
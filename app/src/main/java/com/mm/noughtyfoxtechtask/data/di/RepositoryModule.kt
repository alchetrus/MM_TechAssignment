package com.mm.noughtyfoxtechtask.data.di

import com.mm.noughtyfoxtechtask.data.repository.ApiRepository
import com.mm.noughtyfoxtechtask.data.repository.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsApiRepository(apiRepository: ApiRepositoryImpl): ApiRepository
}
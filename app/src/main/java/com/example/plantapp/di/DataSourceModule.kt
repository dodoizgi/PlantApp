package com.example.plantapp.di

import com.example.plantapp.data.local.CategoryDao
import com.example.plantapp.data.local.LocalDataSource
import com.example.plantapp.data.local.QuestionDao
import com.example.plantapp.data.remote.RemoteDataSource
import com.example.plantapp.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        categoryDao: CategoryDao,
        questionDao: QuestionDao
    ): LocalDataSource {
        return LocalDataSource(categoryDao, questionDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }
} 
package com.example.baseapp.di

import com.example.baseapp.data.repository.Repository
import com.example.baseapp.domain.usecase.GetCategoriesUseCase
import com.example.baseapp.domain.usecase.GetQuestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(repository: Repository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetQuestionsUseCase(repository: Repository): GetQuestionsUseCase {
        return GetQuestionsUseCase(repository)
    }
} 
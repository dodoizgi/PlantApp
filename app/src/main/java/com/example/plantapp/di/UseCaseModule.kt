package com.example.plantapp.di

import com.example.plantapp.data.repository.Repository
import com.example.plantapp.usecase.GetCategoriesUseCase
import com.example.plantapp.usecase.GetQuestionsUseCase
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
package com.example.plantapp.di

import com.example.plantapp.data.repository.RepositoryImpl
import com.example.plantapp.domain.usecase.GetHomeCategoriesUseCase
import com.example.plantapp.domain.usecase.GetHomeQuestionsUseCase
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
    fun provideGetCategoriesUseCase(repositoryImpl: RepositoryImpl): GetHomeCategoriesUseCase {
        return GetHomeCategoriesUseCase(repositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetQuestionsUseCase(repositoryImpl: RepositoryImpl): GetHomeQuestionsUseCase {
        return GetHomeQuestionsUseCase(repositoryImpl)
    }
} 
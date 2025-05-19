package com.example.baseapp.di

import android.content.Context
import androidx.room.Room
import com.example.baseapp.data.local.AppDatabase
import com.example.baseapp.data.local.CategoryDao
import com.example.baseapp.data.repository.Repository
import com.example.baseapp.domain.usecase.GetCategoriesUseCase
import com.example.baseapp.domain.usecase.GetQuestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao {
        return db.categoryDao()
    }
}
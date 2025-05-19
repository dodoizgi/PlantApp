package com.example.plantapp.di

import android.content.Context
import androidx.room.Room
import com.example.plantapp.data.local.AppDatabase
import com.example.plantapp.data.local.CategoryDao
import com.example.plantapp.data.repository.Repository
import com.example.plantapp.domain.usecase.GetCategoriesUseCase
import com.example.plantapp.domain.usecase.GetQuestionsUseCase
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
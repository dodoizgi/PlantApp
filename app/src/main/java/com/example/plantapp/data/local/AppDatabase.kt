package com.example.plantapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantapp.data.model.CategoryEntity
import com.example.plantapp.data.model.QuestionEntity

@Database(entities = [CategoryEntity::class, QuestionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun questionDao(): QuestionDao
}
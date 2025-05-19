package com.example.baseapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseapp.data.model.Question

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
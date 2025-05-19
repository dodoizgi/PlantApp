package com.example.plantapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantapp.data.model.Question

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
package com.example.baseapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories ORDER BY rank ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE title LIKE '%' || :query || '%' ORDER BY rank ASC")
    fun searchCategories(query: String): Flow<List<CategoryEntity>>

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories(): Int
} 
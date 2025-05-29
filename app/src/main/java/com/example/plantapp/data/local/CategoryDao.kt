package com.example.plantapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantapp.data.model.CategoryEntity
import com.example.plantapp.data.model.QuestionEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories ORDER BY id ASC")
    fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE title LIKE '%' || :query || '%' ORDER BY id ASC")
    fun searchCategories(query: String): List<CategoryEntity>

    @Query("DELETE FROM categories")
    fun deleteAllCategories(): Int
}
package com.example.plantapp.data.repository

import com.example.plantapp.data.api.ApiService
import com.example.plantapp.data.local.CategoryDao
import com.example.plantapp.data.local.CategoryEntity
import com.example.plantapp.data.model.Category
import com.example.plantapp.data.model.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val categoryDao: CategoryDao
) {
    suspend fun getCategoriesFromApiAndSaveToDb() {
        try {
            val categories = apiService.getCategories().data
            val categoryEntities = categories.map { it.toCategoryEntity() }
            categoryDao.insertCategories(categoryEntities)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getAllCategoriesFromDb(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { entities ->
            entities.map { it.toCategory() }
        }
    }

    fun searchCategoriesInDb(query: String): Flow<List<Category>> {
        return categoryDao.searchCategories(query).map { entities ->
            entities.map { it.toCategory() }
        }
    }

    private fun Category.toCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            id = this.id,
            name = this.name,
            title = this.title,
            rank = this.rank,
            imageUrl = this.image.url
        )
    }

    private fun CategoryEntity.toCategory(): Category {
        val categoryImage = com.example.plantapp.data.model.CategoryImage(0, "", this.imageUrl) // ID ve name placeholder
        return Category(
            id = this.id,
            name = this.name,
            title = this.title,
            rank = this.rank,
            image = categoryImage
        )
    }

    suspend fun getQuestions(): List<Question> {
        return apiService.getQuestions()
    }

    suspend fun getCategories(): List<Category> {
        getCategoriesFromApiAndSaveToDb()
        return getAllCategoriesFromDb().first()
    }
} 
package com.example.plantapp.data.local

import com.example.plantapp.data.model.CategoryEntity
import com.example.plantapp.data.model.QuestionEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val categoryDao: CategoryDao, private val questionDao: QuestionDao) {
    fun getAllCategories(): List<CategoryEntity> {
        return categoryDao.getAllCategories()
    }

    fun insertCategories(categories: List<CategoryEntity>) {
        categoryDao.insertCategories(categories)
    }

    fun searchCategories(query: String): List<CategoryEntity> {
        return categoryDao.searchCategories(query)
    }

    fun deleteAllCategories() {
        categoryDao.deleteAllCategories()
    }

    fun getAllQuestions(): List<QuestionEntity> {
        return questionDao.getAllQuestions()
    }

    fun insertQuestion(questions: List<QuestionEntity>) {
        questionDao.insertQuestions(questions)
    }
}
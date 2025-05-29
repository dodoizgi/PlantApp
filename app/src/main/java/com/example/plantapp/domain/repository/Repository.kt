package com.example.plantapp.domain.repository

import com.example.plantapp.domain.model.DomainCategory
import com.example.plantapp.domain.model.DomainQuestion

interface Repository {
    fun getCategories(): List<DomainCategory>
    fun getAllCategoriesFromDb(): List<DomainCategory>
    fun searchCategoriesInDb(query: String): List<DomainCategory>
    fun getAllQuestionsFromDb(): List<DomainQuestion>
    fun getQuestions(): List<DomainQuestion>
}
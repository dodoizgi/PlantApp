package com.example.plantapp.data.repository

import com.example.plantapp.data.local.LocalDataSource
import com.example.plantapp.data.model.mapper.DataMapper.toCategory
import com.example.plantapp.data.model.mapper.DataMapper.toCategoryEntity
import com.example.plantapp.data.model.mapper.DataMapper.toDomainCategoryList
import com.example.plantapp.data.model.mapper.DataMapper.toDomainQuestionList
import com.example.plantapp.data.model.mapper.DataMapper.toQuestion
import com.example.plantapp.data.model.mapper.DataMapper.toQuestionEntity
import com.example.plantapp.data.remote.RemoteDataSource
import com.example.plantapp.domain.model.DomainCategory
import com.example.plantapp.domain.model.DomainQuestion
import com.example.plantapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override fun getCategories(): List<DomainCategory> {
        try {
            val categories = remoteDataSource.fetchCategoryData().data
            val categoryEntities = categories.map { it.toCategoryEntity() }
            localDataSource.insertCategories(categoryEntities)
            return categories.toDomainCategoryList()
        } catch (e: Exception) {
            val dbCategories = getAllCategoriesFromDb()
            return dbCategories
        }
    }

    override fun getAllCategoriesFromDb(): List<DomainCategory> {
        return localDataSource.getAllCategories().map { entities ->
            entities.toCategory()
        }
    }

    override fun searchCategoriesInDb(query: String): List<DomainCategory> {
        return localDataSource.searchCategories(query).map { entities ->
            entities.toCategory()
        }
    }

    override fun getAllQuestionsFromDb(): List<DomainQuestion> {
        return localDataSource.getAllQuestions().map { it.toQuestion() }
    }

    override fun getQuestions(): List<DomainQuestion> {
        try {
            val questions = remoteDataSource.fetchQuestionData().data
            val questionEntity = questions.map { it.toQuestionEntity() }
            localDataSource.insertQuestion(questionEntity)
            return questions.toDomainQuestionList()
        } catch (e: Exception) {
            val dbQuestions = getAllQuestionsFromDb()
            return dbQuestions
        }
    }
}
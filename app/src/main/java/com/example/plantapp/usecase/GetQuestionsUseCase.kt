package com.example.plantapp.usecase

import com.example.plantapp.data.model.Question
import com.example.plantapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getQuestions(): Flow<List<Question>> {
        return repository.getQuestions()
    }
} 
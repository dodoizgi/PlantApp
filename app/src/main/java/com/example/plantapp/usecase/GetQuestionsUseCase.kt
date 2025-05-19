package com.example.plantapp.domain.usecase

import com.example.plantapp.data.model.Question
import com.example.plantapp.data.repository.Repository
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Question> {
        return repository.getQuestions()
    }
} 
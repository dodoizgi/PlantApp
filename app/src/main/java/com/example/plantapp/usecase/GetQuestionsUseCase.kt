package com.example.baseapp.domain.usecase

import com.example.baseapp.data.model.Question
import com.example.baseapp.data.repository.Repository
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Question> {
        return repository.getQuestions()
    }
} 
package com.example.plantapp.domain.usecase

import com.example.plantapp.domain.model.DomainQuestion
import com.example.plantapp.domain.repository.Repository
import javax.inject.Inject

class GetHomeQuestionsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): List<DomainQuestion> {
        return repository.getQuestions()
    }
} 
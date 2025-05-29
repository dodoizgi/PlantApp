package com.example.plantapp.domain.usecase

import com.example.plantapp.domain.model.DomainCategory
import com.example.plantapp.domain.repository.Repository
import javax.inject.Inject

class GetHomeCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): List<DomainCategory> {
        return repository.getCategories()
    }
}
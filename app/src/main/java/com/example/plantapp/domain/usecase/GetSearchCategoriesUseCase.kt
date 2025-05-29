package com.example.plantapp.domain.usecase

import com.example.plantapp.domain.model.DomainCategory
import com.example.plantapp.domain.repository.Repository
import javax.inject.Inject

class GetSearchCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(query: String): List<DomainCategory> {
        return repository.searchCategoriesInDb(query)
    }
}
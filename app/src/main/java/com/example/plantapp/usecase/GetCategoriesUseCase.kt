package com.example.plantapp.domain.usecase

import com.example.plantapp.data.model.Category
import com.example.plantapp.data.repository.Repository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
} 
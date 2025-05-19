package com.example.baseapp.domain.usecase

import com.example.baseapp.data.model.Category
import com.example.baseapp.data.repository.Repository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
} 
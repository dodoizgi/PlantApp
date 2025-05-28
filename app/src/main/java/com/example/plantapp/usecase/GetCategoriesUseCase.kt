package com.example.plantapp.usecase

import com.example.plantapp.data.model.Category
import com.example.plantapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories()
    }
}
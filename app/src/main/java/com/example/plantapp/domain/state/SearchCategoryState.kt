package com.example.plantapp.domain.state

import com.example.plantapp.domain.model.DomainCategory

data class SearchCategoryState(
    val isLoading: Boolean = false,
    val data: List<DomainCategory> = emptyList(),
    val error: String? = null
)

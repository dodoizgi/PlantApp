package com.example.plantapp.domain.state

import com.example.plantapp.domain.model.DomainQuestion

data class HomeQuestionState(
    val isLoading: Boolean = false,
    val data: List<DomainQuestion> = emptyList(),
    val error: String? = null
)

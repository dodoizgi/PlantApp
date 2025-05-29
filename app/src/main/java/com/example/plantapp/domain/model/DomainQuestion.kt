package com.example.plantapp.domain.model

data class DomainQuestion(
    val id: Int,
    val title: String,
    val subtitle: String,
    val image_uri: String,
    val uri: String,
    val order: Int
)
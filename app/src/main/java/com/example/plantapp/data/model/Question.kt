package com.example.plantapp.data.model

data class Question(
    val id: Int,
    val title: String,
    val subtitle: String,
    val image_uri: String,
    val uri: String,
    val order: Int
)
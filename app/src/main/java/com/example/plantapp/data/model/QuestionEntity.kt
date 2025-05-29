package com.example.plantapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class  QuestionEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val subtitle: String,
    val image_uri: String,
    val uri: String,
    val order: Int
)
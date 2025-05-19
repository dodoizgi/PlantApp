package com.example.plantapp.data.api

import com.example.plantapp.data.model.CategoryResponse
import com.example.plantapp.data.model.Question
import retrofit2.http.GET

interface ApiService {
    @GET("getCategories")
    suspend fun getCategories(): CategoryResponse

    @GET("getQuestions")
    suspend fun getQuestions(): List<Question>
} 
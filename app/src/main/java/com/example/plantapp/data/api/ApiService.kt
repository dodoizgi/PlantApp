package com.example.baseapp.data.api

import com.example.baseapp.data.model.CategoryResponse
import com.example.baseapp.data.model.Question
import retrofit2.http.GET

interface ApiService {
    @GET("getCategories")
    suspend fun getCategories(): CategoryResponse

    @GET("getQuestions")
    suspend fun getQuestions(): List<Question>
} 
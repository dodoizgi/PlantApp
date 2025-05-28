package com.example.plantapp.data.api

import com.example.plantapp.data.model.CategoryResponse
import com.example.plantapp.data.model.Question
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
    @GET("getCategories")
    fun getCategories(): CategoryResponse

    @GET("getQuestions")
    fun getQuestions(): Flow<List<Question>>
} 
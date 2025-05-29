package com.example.plantapp.data.remote.api

import com.example.plantapp.data.model.DataCategoryResponse
import com.example.plantapp.data.model.DataQuestionResponse
import retrofit2.http.GET

interface ApiService {
    @GET("getCategories")
    fun getCategories(): DataCategoryResponse

    @GET("getQuestions")
    fun getQuestions(): DataQuestionResponse
} 
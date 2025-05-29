package com.example.plantapp.data.remote

import com.example.plantapp.data.model.DataCategoryResponse
import com.example.plantapp.data.model.DataQuestionResponse
import com.example.plantapp.data.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun fetchCategoryData(): DataCategoryResponse {
        return apiService.getCategories()
    }

    fun fetchQuestionData(): DataQuestionResponse {
        return apiService.getQuestions()
    }
}
package com.example.baseapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseapp.data.model.Category
import com.example.baseapp.data.model.Question
import com.example.baseapp.data.repository.Repository
import com.example.baseapp.domain.usecase.GetCategoriesUseCase
import com.example.baseapp.domain.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {
    val categories: LiveData<List<Category>> = repository.getAllCategoriesFromDb().asLiveData()

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadCategoriesAndQuestions()
    }

    private fun loadCategoriesAndQuestions() {
        viewModelScope.launch {
            try {
                repository.getCategoriesFromApiAndSaveToDb()
                _questions.value = repository.getQuestions()
            } catch (e: Exception) {
                _error.value = e.message ?: "Kategoriler veya sorular yüklenirken bir hata oluştu."
            }
        }
    }
} 
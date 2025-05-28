package com.example.plantapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapp.data.model.Category
import com.example.plantapp.data.model.Question
import com.example.plantapp.usecase.GetCategoriesUseCase
import com.example.plantapp.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    lateinit var categories: StateFlow<List<Category>>
    lateinit var questions: StateFlow<List<Question>>

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadCategories()
        loadQuestions()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                categories = getCategoriesUseCase
                    .invoke()
                    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

            } catch (e: Exception) {
                _error.value = e.message ?: "Kategoriler yüklenirken bir hata oluştu."
            }
        }
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            try {
                questions = getQuestionsUseCase
                    .getQuestions()
                    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

            } catch (e: Exception) {
                _error.value = e.message ?: "Ssorular yüklenirken bir hata oluştu."
            }
        }
    }
} 
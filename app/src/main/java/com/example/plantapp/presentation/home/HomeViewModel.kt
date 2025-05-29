package com.example.plantapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapp.domain.state.HomeCategoryState
import com.example.plantapp.domain.state.HomeQuestionState
import com.example.plantapp.domain.usecase.GetHomeCategoriesUseCase
import com.example.plantapp.domain.usecase.GetHomeQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeCategoriesUseCase: GetHomeCategoriesUseCase,
    private val getHomeQuestionsUseCase: GetHomeQuestionsUseCase
) : ViewModel() {

    private val _categoriesState = MutableStateFlow(HomeCategoryState())
    val categoriesState: StateFlow<HomeCategoryState> = _categoriesState.asStateFlow()

    private val _questionsState = MutableStateFlow(HomeQuestionState())
    val questionsState: StateFlow<HomeQuestionState> = _questionsState.asStateFlow()

    init {
        loadCategories()
        loadQuestions()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _categoriesState.value = HomeCategoryState(isLoading = true)
            try {
                val data = getHomeCategoriesUseCase()
                _categoriesState.value = HomeCategoryState(data = data)
            } catch (e: Exception) {
                _categoriesState.value = HomeCategoryState(error = e.message)
            }
        }
    }

    fun loadQuestions() {
        viewModelScope.launch {
            _questionsState.value = HomeQuestionState(isLoading = true)
            try {
                val data = getHomeQuestionsUseCase()
                _questionsState.value = HomeQuestionState(data = data)
            } catch (e: Exception) {
                _questionsState.value = HomeQuestionState(error = e.message)
            }
        }
    }
} 
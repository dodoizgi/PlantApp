package com.example.plantapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapp.domain.state.SearchCategoryState
import com.example.plantapp.domain.usecase.GetSearchCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchCategoriesUseCase: GetSearchCategoriesUseCase
) : ViewModel() {

    private val _searchCategoryState = MutableStateFlow(SearchCategoryState())
    val searchState: StateFlow<SearchCategoryState> = _searchCategoryState.asStateFlow()

    fun searchCategories(query: String) {
        viewModelScope.launch {
            _searchCategoryState.value = SearchCategoryState(isLoading = true)
            try {
                val data = getSearchCategoriesUseCase(query)
                _searchCategoryState.value = SearchCategoryState(data = data)
            } catch (e: Exception) {
                _searchCategoryState.value = SearchCategoryState(error = e.message)
            }
        }
    }
} 
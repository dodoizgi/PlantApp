package com.example.plantapp.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.FragmentNavigationListener
import com.example.plantapp.databinding.FragmentHomeBinding
import com.example.plantapp.presentation.base.BaseFragment
import com.example.plantapp.presentation.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var errorManager: HomeErrorManager
    private lateinit var navigationListener: FragmentNavigationListener

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationListener = requireActivity() as FragmentNavigationListener
        initView()
        setupObservers()
    }

    private fun initView() = with(binding) {
        errorManager = HomeErrorManager(requireContext())

        categoriesAdapter = CategoriesAdapter()
        recyclerCategories.layoutManager = GridLayoutManager(context, 2)
        recyclerCategories.adapter = categoriesAdapter

        questionAdapter = QuestionAdapter { question ->
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(question.uri))
                startActivity(intent)
            } catch (e: Exception) {
                errorManager.handleError("URL açılamadı: ${e.message}")
            }
        }
        recyclerQuestions.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerQuestions.adapter = questionAdapter

        editSearch.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                navigationListener.loadFragment(SearchFragment())
            }
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.categoriesState.collect { state ->
                when {
                    state.isLoading -> {
                        // Yükleme göstergesi göster
                    }

                    state.error != null -> {
                        errorManager.handleError(state.error)
                    }

                    else -> {
                        categoriesAdapter.submitList(state.data)
                    }
                }
            }

            viewModel.questionsState.collect { state ->
                when {
                    state.isLoading -> {
                        // Yükleme göstergesi göster
                    }

                    state.error != null -> {
                        errorManager.handleError(state.error)
                    }

                    else -> {
                        questionAdapter.submitList(state.data)
                    }
                }
            }
        }
    }
} 
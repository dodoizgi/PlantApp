package com.example.plantapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantapp.FragmentNavigationListener
import com.example.plantapp.databinding.FragmentSearchBinding
import com.example.plantapp.presentation.base.BaseFragment
import com.example.plantapp.presentation.home.CategoriesAdapter
import com.example.plantapp.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private lateinit var navigationListener: FragmentNavigationListener

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationListener = requireActivity() as FragmentNavigationListener
        initView()
    }

    private fun initView() = with(binding) {
        categoriesAdapter = CategoriesAdapter()
        recyclerCategories.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoriesAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchState.collect { state ->
                when {
                    state.isLoading -> {
                    }

                    state.error != null -> {
                    }

                    else -> {
                        categoriesAdapter.submitList(state.data)
                    }
                }
            }
        }

        editSearch.requestFocus()

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchCategories(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        closeButton.setOnClickListener {
            navigationListener.loadFragment(HomeFragment())
        }
    }
} 
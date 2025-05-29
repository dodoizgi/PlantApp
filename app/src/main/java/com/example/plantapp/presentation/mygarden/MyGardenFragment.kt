package com.example.plantapp.presentation.mygarden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.R
import com.example.plantapp.data.model.Plant
import com.example.plantapp.databinding.FragmentMyGardenBinding
import com.example.plantapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyGardenFragment : BaseFragment<FragmentMyGardenBinding>() {

    private lateinit var myGardenAdapter: MyGardenAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyGardenBinding {
        return FragmentMyGardenBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadPlants()
    }

    private fun setupRecyclerView() {
        myGardenAdapter = MyGardenAdapter()
        binding.recyclerMyGarden.layoutManager = LinearLayoutManager(context)
        binding.recyclerMyGarden.adapter = myGardenAdapter
    }

    private fun loadPlants() {
        val plants = listOf(
            Plant("Gül", R.drawable.ic_my_garden),
            Plant("Papatya", R.drawable.ic_my_garden),
            Plant("Lale", R.drawable.ic_my_garden)
        )
        myGardenAdapter.submitList(plants)
    }
}
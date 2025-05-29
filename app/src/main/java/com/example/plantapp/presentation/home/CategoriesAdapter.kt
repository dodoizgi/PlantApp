package com.example.plantapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.R
import com.example.plantapp.databinding.ItemCategoryBinding
import com.example.plantapp.domain.model.DomainCategory

class CategoriesAdapter : ListAdapter<DomainCategory, CategoriesAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(homeDataCategory: DomainCategory) {
            binding.textCategoryName.text = homeDataCategory.title

            Glide.with(binding.root.context)
                .load(homeDataCategory.image.url)
                .placeholder(R.drawable.bg_search)
                .centerCrop()
                .into(binding.imageCategoryBackground)
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<DomainCategory>() {
        override fun areItemsTheSame(oldItem: DomainCategory, newItem: DomainCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DomainCategory, newItem: DomainCategory): Boolean {
            return oldItem == newItem
        }
    }
} 
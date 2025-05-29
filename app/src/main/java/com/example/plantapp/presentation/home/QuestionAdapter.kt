package com.example.plantapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.R
import com.example.plantapp.databinding.ItemQuestionBinding
import com.example.plantapp.domain.model.DomainQuestion

class QuestionAdapter(private val onItemClick: (DomainQuestion) -> Unit) :
    ListAdapter<DomainQuestion, QuestionAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class QuestionViewHolder(
        private val binding: ItemQuestionBinding,
        private val onItemClick: (DomainQuestion) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(domainQuestion: DomainQuestion) {
            binding.textCategoryName.text = domainQuestion.title
            Glide.with(binding.root.context)
                .load(domainQuestion.image_uri)
                .placeholder(R.drawable.bg_indicator_inactive)
                .centerCrop()
                .into(binding.imageCategoryBackground)

            itemView.setOnClickListener {
                onItemClick(domainQuestion)
            }
        }
    }

    class QuestionDiffCallback : DiffUtil.ItemCallback<DomainQuestion>() {
        override fun areItemsTheSame(
            oldItem: DomainQuestion,
            newItem: DomainQuestion
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DomainQuestion,
            newItem: DomainQuestion
        ): Boolean {
            return oldItem == newItem
        }
    }
} 
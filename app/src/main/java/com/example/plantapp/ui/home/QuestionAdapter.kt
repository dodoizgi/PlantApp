package com.example.plantapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.R
import com.example.plantapp.data.model.Question
import com.example.plantapp.databinding.ItemQuestionBinding

class QuestionAdapter(private val onItemClick: (Question) -> Unit) : ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class QuestionViewHolder(private val binding: ItemQuestionBinding, private val onItemClick: (Question) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.textCategoryName.text = question.title
            Glide.with(binding.root.context)
                .load(question.image_uri)
                .placeholder(R.drawable.bg_indicator_inactive)
                .centerCrop()
                .into(binding.imageCategoryBackground)

            itemView.setOnClickListener {
                onItemClick(question)
            }
        }
    }

    class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }
} 
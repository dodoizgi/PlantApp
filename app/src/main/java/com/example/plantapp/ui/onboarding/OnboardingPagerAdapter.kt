package com.example.plantapp.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapp.R
import com.example.plantapp.databinding.ItemOnboardingPageBinding

class OnboardingPagerAdapter(
    private val items: List<OnboardingPage>,
    private val onLastPageButtonClick: () -> Unit,
    private val onNextPageButtonClick: (Int) -> Unit
) : RecyclerView.Adapter<OnboardingPagerAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val binding = ItemOnboardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position, items.size, position == items.lastIndex, onLastPageButtonClick, onNextPageButtonClick)
    }

    override fun getItemCount(): Int = items.size

    class OnboardingViewHolder(private val binding: ItemOnboardingPageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OnboardingPage, position: Int, itemCount: Int, isLast: Boolean, onLastPageButtonClick: () -> Unit, onNextPageButtonClick: (Int) -> Unit) {
            with(binding) {
                imageOnboarding.setImageResource(item.imageRes)
                textOnboardingTitle.text = item.title
                textOnboardingDesc.text = item.desc
                textOnboardingTerms.visibility = if (position == 0) View.VISIBLE else View.GONE

                layoutPageIndicator.visibility = if (position == 0) View.GONE else View.VISIBLE
                if (layoutPageIndicator.visibility == View.VISIBLE) {
                   setupIndicators(itemCount, position)
                }

                buttonOnboarding.apply {
                    text = if (position == 0) "Get Started" else "Continue"
                    setOnClickListener {
                        if (isLast) {
                            onLastPageButtonClick()
                        } else {
                            onNextPageButtonClick(position)
                        }
                    }
                }
            }
        }
        
        private fun setupIndicators(itemCount: Int, currentPosition: Int) {
            val indicators = Array(itemCount) { ImageView(binding.root.context) }
            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            binding.layoutPageIndicator.removeAllViews()
            indicators.forEachIndexed { index, imageView ->
                val drawableRes = if (index == currentPosition) {
                    when (currentPosition) {
                        1 -> R.drawable.indicator_one
                        2 -> R.drawable.indicator_two
                        else -> R.drawable.bg_indicator_active
                    }
                } else {
                    R.drawable.bg_indicator_inactive
                }
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.root.context,
                        drawableRes
                    )
                )
                val marginParams = ViewGroup.MarginLayoutParams(params)
                marginParams.setMargins(8, 0, 8, 0)
                imageView.layoutParams = marginParams
                binding.layoutPageIndicator.addView(imageView)
            }
        }
    }
}

data class OnboardingPage(
    val imageRes: Int,
    val title: CharSequence,
    val desc: String
) 
package com.example.plantapp.ui.onboarding

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.plantapp.R
import com.example.plantapp.databinding.FragmentOnboardingBinding
import com.example.plantapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    private lateinit var adapter: OnboardingPagerAdapter

    private val pages = listOf(
        OnboardingPage(
            imageRes = R.drawable.onboarding_1,
            title = SpannableString("Welcome to PlantApp").apply {
                setSpan(StyleSpan(Typeface.BOLD), 11, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            },
            desc = "Identify more than 3000+ plants and 88% accuracy."
        ),
        OnboardingPage(
            imageRes = R.drawable.onboarding_2,
            title = SpannableString("Take a photo to identify the plant!").apply {
                setSpan(StyleSpan(Typeface.BOLD), 16, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            },
            desc = ""
        ),
        OnboardingPage(
            imageRes = R.drawable.onboarding_3,
            title = SpannableString("Get plant care guides").apply {
                setSpan(StyleSpan(Typeface.BOLD), 10, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            },
            desc = ""
        )
    )

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OnboardingPagerAdapter(
            pages,
            onLastPageButtonClick = {
                requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
                    .edit().putBoolean("onboarding_completed", true).apply()
                findNavController().navigate(R.id.action_onboardingFragment_to_paywallFragment)
            },
            onNextPageButtonClick = { currentPosition ->
                binding.viewpagerOnboarding.currentItem = currentPosition + 1
            }
        )
        binding.viewpagerOnboarding.adapter = adapter
    }
} 
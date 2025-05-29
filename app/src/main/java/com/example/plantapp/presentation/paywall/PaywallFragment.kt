package com.example.plantapp.presentation.paywall

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.plantapp.FragmentNavigationListener
import com.example.plantapp.R
import com.example.plantapp.databinding.FragmentPaywallBinding
import com.example.plantapp.presentation.base.BaseFragment
import com.example.plantapp.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaywallFragment : BaseFragment<FragmentPaywallBinding>() {
    private lateinit var navigationListener: FragmentNavigationListener


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPaywallBinding {
        return FragmentPaywallBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationListener = requireActivity() as FragmentNavigationListener
        initView()
        selectYearlyOption()
    }

    private fun initView() = with(binding) {
        var sharedPreferences =
            requireContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        buttonTryFree.setOnClickListener {
            navigationCompleted(sharedPreferences)
        }

        closeButton.setOnClickListener {
            navigationCompleted(sharedPreferences)
        }
        layoutPriceMonth.setOnClickListener { selectMonthlyOption() }
        layoutPriceYear.setOnClickListener { selectYearlyOption() }
    }

    private fun navigationCompleted(sharedPreferences: SharedPreferences) {
        sharedPreferences.edit().putBoolean("is_onboarding_completed", true).apply()
        navigationListener.loadFragment(HomeFragment())
    }

    private fun selectMonthlyOption() = with(binding) {
        layoutPriceMonth.isSelected = true
        layoutPriceYear.isSelected = false
        imageRadioMonth.setImageResource(R.drawable.ic_radio_checked)
        imageRadioYear.setImageResource(R.drawable.ic_radio_unchecked)
        textSavePercentage.visibility = GONE
        buttonTryFree.text = getText(R.string.one_month_button_text)
    }

    private fun selectYearlyOption() = with(binding) {
        layoutPriceYear.isSelected = true
        layoutPriceMonth.isSelected = false
        imageRadioYear.setImageResource(R.drawable.ic_radio_checked)
        imageRadioMonth.setImageResource(R.drawable.ic_radio_unchecked)
        textSavePercentage.visibility = VISIBLE
        buttonTryFree.text = getText(R.string.try_button_text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
} 
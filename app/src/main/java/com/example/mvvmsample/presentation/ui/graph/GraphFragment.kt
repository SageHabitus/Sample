package com.example.mvvmsample.presentation.ui.graph

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.mvvmsample.R
import com.example.mvvmsample.databinding.FragmentGraphBinding
import com.example.mvvmsample.presentation.binding.BindingFragment
import com.example.mvvmsample.presentation.common.Currency
import com.example.mvvmsample.presentation.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class GraphFragment : BindingFragment<FragmentGraphBinding>(FragmentGraphBinding::inflate) {

    private val mainViewModel: MainActivityViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        observeCommand()
    }

    private fun setOnClickListener() = with(binding) {
        val buttonCurrencyMap = mapOf(
            btnUsd to Currency.USD,
            btnJpy to Currency.JPY,
            btnEur to Currency.EUR
        )

        buttonCurrencyMap.forEach { (btn, currency) ->
            btn.setOnClickListener {
                if (it.isSelected) return@setOnClickListener
                buttonCurrencyMap.keys.forEach { btn -> btn.isSelected = it == btn }
                mainViewModel.getCurrencies(currency)
            }
        }
        btnShowlist.setOnClickListener {
            findNavController().navigate(R.id.action_graph_fragment_to_list_fragment)
        }
    }

    private fun observeCommand() = repeatOnStarted {
        mainViewModel.uiState.collect {
            with(binding) {
                tvDate.text = it.selectedDate
                lineGraph.setData(it.currencyItemUiState)
            }
        }
    }
}
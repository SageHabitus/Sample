package com.example.mvvmsample.presentation.ui.list

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.mvvmsample.R
import com.example.mvvmsample.databinding.FragmentListBinding
import com.example.mvvmsample.presentation.binding.BindingFragment
import com.example.mvvmsample.presentation.ui.MainActivityViewModel
import com.example.mvvmsample.presentation.ui.list.recyclerview.CurrencyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class ListFragment : BindingFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val mainViewModel: MainActivityViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    private val adapter by lazy { CurrencyAdapter { } }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setTitle()
        setOnSpinner()
        setOnListener()
        onBackPressed()
        observeCommand()
    }

    private fun setTitle() = with(binding) {
        tvTitleCurrency.text = mainViewModel.uiState.value.selectedCurrency.uppercase()
    }

    private fun setRecyclerView() = with(binding.rvCurrency) {
        setHasFixedSize(true)
        adapter = this@ListFragment.adapter

    }

    private fun setOnSpinner() = with(binding.spinner) {
        val items = listOf(
            "2023-09-04",
            "2023-09-05",
            "2023-09-06",
            "2023-09-07",
            "2023-09-08"
        )

        this.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        setSelection(mainViewModel.uiState.value.selectedDateIndex)

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainViewModel.setDate(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setOnListener() = with(binding) {
        ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeCommand() = repeatOnStarted {
        mainViewModel.uiState.collect {
            adapter.submitList(it.currencyItemUiState) {
                binding.rvCurrency.scrollToPosition(it.currencyItemUiState.count() - 1)
            }
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }
}
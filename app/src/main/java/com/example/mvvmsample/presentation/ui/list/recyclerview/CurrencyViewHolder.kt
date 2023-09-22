package com.example.mvvmsample.presentation.ui.list.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsample.databinding.RvItemCurrencyBinding
import com.example.mvvmsample.presentation.uistate.CurrencyItemUiState

class CurrencyViewHolder(
    private val binding: RvItemCurrencyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiState: CurrencyItemUiState) = with(binding) {
        tvDate.text = uiState.applyDate
        tvTime.text = uiState.applyTime
        tvRate.text = uiState.tradeStanRate
    }

    companion object {
        operator fun invoke(parent: ViewGroup) =
            CurrencyViewHolder(
                RvItemCurrencyBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

}
package com.example.mvvmsample.presentation.ui.list.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mvvmsample.presentation.uistate.CurrencyItemUiState

class CurrencyAdapter(
    private val onItemClickListener: (CurrencyItemUiState) -> Unit
) : ListAdapter<CurrencyItemUiState, CurrencyViewHolder>(this) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(parent).apply {
            itemView.setOnClickListener {
                onItemClickListener(getItem(adapterPosition))
            }
        }


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) =
        holder.bind(getItem(position))


    companion object : DiffUtil.ItemCallback<CurrencyItemUiState>() {
        override fun areItemsTheSame(
            oldItem: CurrencyItemUiState,
            newItem: CurrencyItemUiState
        ) = oldItem.applyTime == newItem.applyTime

        override fun areContentsTheSame(
            oldItem: CurrencyItemUiState,
            newItem: CurrencyItemUiState
        ) = oldItem == newItem


    }
}
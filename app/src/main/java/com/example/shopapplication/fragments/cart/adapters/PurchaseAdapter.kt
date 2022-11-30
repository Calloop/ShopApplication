package com.example.shopapplication.fragments.cart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shopapplication.databinding.CartRecyclerItemBinding
import com.example.domain.impl.entities.ProductBasket

class PurchaseAdapter(
    private val onCounterPlusClick: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit,
    private val onCounterMinusClick: (Int) -> Unit
): ListAdapter<ProductBasket, PurchaseViewHolder>(PurchaseAdapterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder(
            CartRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onCounterPlusClick = onCounterPlusClick,
            onDeleteClick = onDeleteClick,
            onCounterMinusClick = onCounterMinusClick
        )
    }
}

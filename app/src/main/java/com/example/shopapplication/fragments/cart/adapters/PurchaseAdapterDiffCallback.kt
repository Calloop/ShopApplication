package com.example.shopapplication.fragments.cart.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.impl.entities.ProductBasket

class PurchaseAdapterDiffCallback: DiffUtil.ItemCallback<ProductBasket>() {
    override fun areItemsTheSame(oldItem: ProductBasket, newItem: ProductBasket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductBasket, newItem: ProductBasket): Boolean {
        return oldItem == newItem
    }
}
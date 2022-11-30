package com.example.shopapplication.fragments.cart.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shopapplication.databinding.CartRecyclerItemBinding
import com.example.domain.impl.entities.ProductBasket

class PurchaseViewHolder(
    private val binding: CartRecyclerItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: ProductBasket,
        onCounterPlusClick: (Int) -> Unit,
        onDeleteClick: (Int) -> Unit,
        onCounterMinusClick: (Int) -> Unit
    ) {
        binding.itemButtonCounterPlus.setOnClickListener {
            onCounterPlusClick(bindingAdapterPosition)
            bindingAdapter?.notifyItemChanged(bindingAdapterPosition)
        }
        binding.itemButtonDelete.setOnClickListener {
            onDeleteClick(bindingAdapterPosition)
            bindingAdapter?.notifyItemChanged(bindingAdapterPosition)
        }
        binding.itemButtonCounterMinus.setOnClickListener {
            onCounterMinusClick(bindingAdapterPosition)
            bindingAdapter?.notifyItemChanged(bindingAdapterPosition)
        }

        binding.itemImage.load(item.pictureUrl)
        binding.itemTextCount.text = item.count.toString()
        binding.itemTextName.text = item.name
        binding.itemTextPrice.text = buildString {
            append("$")
            append(item.price.toString())
        }
    }
}
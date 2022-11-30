package com.example.shopapplication.fragments.details.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shopapplication.R
import com.example.shopapplication.databinding.DetailRecyclerImagesBinding

class DetailsViewHolder(
    private val binding: DetailRecyclerImagesBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.itemImage.load(item) {
            placeholder(R.drawable.picture_samsung)
        }
    }
}
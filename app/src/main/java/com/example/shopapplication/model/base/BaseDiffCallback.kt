package com.example.shopapplication.model.base

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.impl.entities.delegate.Delegate

open class BaseDiffCallback: DiffUtil.ItemCallback<Delegate>() {
    override fun areItemsTheSame(oldItem: Delegate, newItem: Delegate): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Delegate, newItem: Delegate): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
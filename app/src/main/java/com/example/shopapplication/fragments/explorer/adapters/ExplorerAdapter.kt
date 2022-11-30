package com.example.shopapplication.fragments.explorer.adapters

import com.example.domain.impl.entities.delegate.Delegate
import com.example.shopapplication.fragments.explorer.adapters.delegates.Delegates
import com.example.shopapplication.model.base.BaseDiffCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ExplorerAdapter(
    private val onCategoryClick: (Int) -> Unit,
    private val onSellerFavoriteClick: (Int) -> Unit,
    private val onSellerItemClick: (Int) -> Unit
) : AsyncListDifferDelegationAdapter<Delegate>(BaseDiffCallback()) {
    init {
        delegatesManager.addDelegate(Delegates.categoryDelegate(
            onClick = { onCategoryClick(it)
            }
        ))
            .addDelegate(Delegates.searchDelegate)
            .addDelegate(Delegates.hotDelegate())
            .addDelegate(Delegates.sellersDelegate(
                onFavoriteClick = { onSellerFavoriteClick(it) },
                onItemClick = { onSellerItemClick(it) }
            ))
    }
}
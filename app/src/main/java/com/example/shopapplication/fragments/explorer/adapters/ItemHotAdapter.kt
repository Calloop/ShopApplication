package com.example.shopapplication.fragments.explorer.adapters

import com.example.domain.impl.entities.delegate.Delegate
import com.example.shopapplication.fragments.explorer.adapters.delegates.Delegates
import com.example.shopapplication.model.base.BaseDiffCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ItemHotAdapter : AsyncListDifferDelegationAdapter<Delegate>(BaseDiffCallback()) {
    init {
        delegatesManager.addDelegate(Delegates.itemHotDelegate)
    }
}
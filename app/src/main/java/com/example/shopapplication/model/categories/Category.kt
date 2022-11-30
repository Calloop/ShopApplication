package com.example.shopapplication.model.categories

import com.example.domain.impl.entities.delegate.CategoryDelegate

data class Category(
    override var id: Int,
    val name: String,
    val iconId: Int,
    var isClicked: Boolean
): CategoryDelegate
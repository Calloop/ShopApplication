package com.example.domain.impl.entities.delegate.data

import com.example.domain.impl.entities.delegate.CategoryDelegate

data class CategoryDelegateImpl(
    override val id: Int,
    var categoryData: List<CategoryDelegate>
): CategoryDelegate
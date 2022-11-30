package com.example.domain.impl.entities

import com.example.domain.impl.entities.delegate.SellerDelegate

data class ProductSeller(
    override var id: Int,
    val name: String,
    val pictureUrl: String,
    var isFavorite: Boolean = false,
    val price: Int,
    val discountPrice: Int?,
): SellerDelegate
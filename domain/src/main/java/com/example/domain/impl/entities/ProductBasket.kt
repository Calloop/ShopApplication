package com.example.domain.impl.entities

data class ProductBasket(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val price: Int,
    var count: Int,
    val fullPrice: Int = price * count
)

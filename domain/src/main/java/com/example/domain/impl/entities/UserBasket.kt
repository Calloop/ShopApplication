package com.example.domain.impl.entities

data class UserBasket(
    val id: Int,
    val products: List<ProductBasket>,
    val deliveryCost: String,
    val totalCost: Int
)

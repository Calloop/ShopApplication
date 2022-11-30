package com.example.domain.impl.entities

data class ProductDetail(
    val id: Int,
    val name: String,
    val price: Int,
    val rating: Float,
    val isFavorite: Boolean,
    val pictureUrls: List<String>,
    val specs: PhoneSpecification
)

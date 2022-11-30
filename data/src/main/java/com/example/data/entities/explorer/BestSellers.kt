package com.example.data.entities.explorer

import com.google.gson.annotations.SerializedName

data class BestSellers(
    @SerializedName("id") val id: Int,
    @SerializedName("is_favorites") val isFavorites: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("price_without_discount") val discountPrice: Int,
    @SerializedName("discount_price") val price: Int,
    @SerializedName("picture") val pictureUrl: String
)

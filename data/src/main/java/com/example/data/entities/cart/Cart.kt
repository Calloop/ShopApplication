package com.example.data.entities.cart

import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("id") val userId: Int,
    @SerializedName("delivery") val deliveryPrice: String,
    @SerializedName("total") val totalPrice: Int,
    @SerializedName("basket") val basketItems: List<BasketItems>
)
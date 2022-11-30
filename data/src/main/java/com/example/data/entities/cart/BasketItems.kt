package com.example.data.entities.cart

import com.google.gson.annotations.SerializedName

data class BasketItems(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("images") val pictureUrl: String,
    @SerializedName("price") val price: Int
)

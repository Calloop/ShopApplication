package com.example.data.entities.explorer

import com.google.gson.annotations.SerializedName

data class HomeStore(
    @SerializedName("id") val id: Int,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("picture") val pictureUrl: String,
    @SerializedName("is_buy") val isBuy: Boolean
)

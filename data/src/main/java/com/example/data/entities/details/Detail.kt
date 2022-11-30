package com.example.data.entities.details

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("isFavorites") val isFavorite: Boolean,
    @SerializedName("images") val pictureUrls: List<String>,
    @SerializedName("price") val price: Int,
    @SerializedName("sd") val storage: String,
    @SerializedName("ssd") val ram: String,
    @SerializedName("CPU") val cpu: String,
    @SerializedName("camera") val camera: String,
    @SerializedName("capacity") val capacity: List<Int>,
    @SerializedName("color") val colors: List<String>
)